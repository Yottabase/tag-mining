package org.yottabase.tagmining.phraseextractor;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.WebPage;

public class PhraseExtractor implements InterfacePhraseExtractor {
	
	/*
	 * Reference: https://html.spec.whatwg.org/multipage/semantics.html
	 */
	
	private static final String PUNCTUATION = "(?<=[\\.?!;|]\\s)";
	
	private static final String REGEX_BLANKS = "\\s+";
	
	private static final String REGEX_TRIM = "(\\W\\D)+$|^(\\W\\D)+";
	
	private static final String XPATH_EXTRACTOR = "//body//text()";
	
	private static final String[] SKIPPED_TAGS = {"head", "meta", "figure", "img", "script", "style", "option" };

	private static final int MIN_WORDS = 3;
	private static final int MIN_CHARS = 5;
	
	private int textsSkippedByLiATags = 0;
	private int textsSkippedByBlacklistedTags = 0;
	private int phrasesSkippedByFewChars = 0;
	private int phrasesSkippedByFewWords = 0;
	private int textsFound = 0;
	private int acceptedPhrasesFound = 0;
	
	@Override
	public List<Phrase> extractPhrases(WebPage htmlPage) {
		
		List<Phrase> phrases = new ArrayList<Phrase>();
		
		TagNode tagNode = new HtmlCleaner().clean(htmlPage.getPageHtml());
		try {
			
			CleanerProperties cleanerProperties = new CleanerProperties();	
			Document doc = new DomSerializer(cleanerProperties).createDOM(tagNode);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(XPATH_EXTRACTOR, doc, XPathConstants.NODESET);
			this.textsFound += nodes.getLength();
			
			for (int i = 0; i < nodes.getLength(); ++i) {
				
				Node e = nodes.item(i);
				
				if(! this.isAcceptableNode(e) ) continue;
				
				List<Phrase> phrasesFound = this.extractPhrasesFromNode(htmlPage.getTrecID(), e);
				this.acceptedPhrasesFound += phrasesFound.size();
				
				phrases.addAll(phrasesFound);
			}
			
		} catch(DOMException e){
			System.err.println("An invalid or illegal HTML character is specified.");
			
		} catch (ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return phrases;
	}
	
	public boolean isAcceptableNode(Node node){
		String tagName = "", parentTagName = "";
		
		tagName = node.getParentNode().getNodeName();
		if(Arrays.asList(SKIPPED_TAGS).contains(tagName)) {
			this.textsSkippedByBlacklistedTags++;
			return false;
		}
		
		try{
			parentTagName = node.getParentNode().getParentNode().getNodeName();
			if(tagName.equals("a") && parentTagName.equals("li")) {
				this.textsSkippedByLiATags++;
				return false;	
			}
		}catch(NullPointerException e1){
			//This exception is not relevant
		}
		
		return true;
	}
	
	public List<Phrase> extractPhrasesFromNode(String trecId, Node node){
		
		List<Phrase> phrases = new LinkedList<Phrase>();
		
		String text = node.getNodeValue();
		
		text = text.replaceAll(REGEX_BLANKS, " ");
		
		for(String t : text.split(PUNCTUATION)){
			
			t = t.replaceAll(REGEX_TRIM, "");
			
			if(t.length() < MIN_CHARS) {
				this.phrasesSkippedByFewChars++;
				continue;
			}
			
			if(t.split("\\s").length < MIN_WORDS) {
				this.phrasesSkippedByFewWords++;
				continue;
			}
			
			phrases.add(new Phrase(trecId, t));
		}
		
		return phrases;
	}
	
	public void printNode(Node node){
		StringWriter writer = new StringWriter();
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(node), new StreamResult(writer));			 
		} catch (TransformerFactoryConfigurationError | TransformerException e2) {
			e2.printStackTrace();
		}
		
		System.out.println(writer.toString());
	}
	
	public String getStats(){
		return String.format(
				  "textsSkippedByLiATags:\t\t%s\n"
				+ "textsSkippedByBlacklistedTags:\t%s\n"
				+ "textsFound:\t\t\t%s\n"
				+ "phrasesSkippedByFewChars:\t%s\n"
				+ "phrasesSkippedByFewWords:\t%s\n" 
				+ "acceptedPhrasesFound:\t\t%s\n", 
			this.textsSkippedByLiATags, this.textsSkippedByBlacklistedTags, this.textsFound, 
			this.phrasesSkippedByFewChars, this.phrasesSkippedByFewWords,  this.acceptedPhrasesFound
		);
				
	}
}
