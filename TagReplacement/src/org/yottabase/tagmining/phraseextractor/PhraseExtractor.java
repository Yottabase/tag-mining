package org.yottabase.tagmining.phraseextractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
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
	
	private static final String XPATH_EXTRACTOR = "//body//text()";
	
	private static final String[] SKIPPED_TAGS = {"body", "head", "meta", "figure", "img", "script", "style", "option" };

	private static final int MIN_WORDS = 3;
	private static final int MIN_CHARS = 5;
	
	@Override
	public List<Phrase> extractPhrases(WebPage htmlPage) {
		
		List<Phrase> phrases = new ArrayList<Phrase>();
		
		TagNode tagNode = new HtmlCleaner().clean(htmlPage.getPageHtml());
		try {
			
			CleanerProperties cleanerProperties = new CleanerProperties();	
			Document doc = new DomSerializer(cleanerProperties).createDOM(tagNode);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(XPATH_EXTRACTOR, doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nodes.getLength(); ++i) {
				
				String tagName = "", parentTagName = "";
				
				Node e = nodes.item(i);
				
				tagName = e.getParentNode().getNodeName();
				if(Arrays.asList(SKIPPED_TAGS).contains(tagName)) continue;
				
				try{
					parentTagName = e.getParentNode().getParentNode().getNodeName();
					if(tagName.equals("a") && parentTagName.equals("li")) continue;	
				}catch(NullPointerException e1){
					//This exception is not relevant
				}
				
				String text = e.getNodeValue();
				text = text.replaceAll("\\r\\n|\\r|\\n|\\s+", " ");
				
				for(String t : text.split(PUNCTUATION)){
					t = t.trim();
					if(t.length() < MIN_CHARS || t.split("\\s").length < MIN_WORDS) continue;
					
					phrases.add(new Phrase(htmlPage.getTrecID(), t));
				}
			}
			
		} catch(DOMException e){
			System.err.println("An invalid or illegal HTML character is specified.");
			
		} catch (ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return phrases;
	}
}
