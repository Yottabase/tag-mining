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
	
	private static final String REGEX_TRIM = "^([^\\w]+)|([^a-z0-9\\?\\.!;]+)$";
	
	private static final String XPATH_EXTRACTOR = "//body//text()/..";
	
	private static final String[] SKIPPED_TAGS = {"head", "meta", "figure", "img", "script", "style", "option", "form" };
	
	private static final String[] INLINE_TAGS = {"span", "a", "em", "strong", "small", "abbr", "data", "time", "sub", "sup", "i", "b", "u", "mark"};

	private static final int MIN_WORDS = 3;
	private static final int MIN_CHARS = 5;
	
	private int textsSkippedByLiATags = 0;
	private int textsSkippedByBlacklistedTags = 0;
	private int textsFound = 0;
	private int phrasesSkippedByFewChars = 0;
	private int phrasesSkippedByFewWords = 0;
	private int acceptedPhrasesFound = 0;
	
	HtmlCleaner htmlCleaner;
	
	DomSerializer domSerializer;
	
	public PhraseExtractor() {
		
		CleanerProperties cleanerProperties = new CleanerProperties();	
		cleanerProperties.setOmitComments(true);
		cleanerProperties.setPruneTags(String.join(",", SKIPPED_TAGS));
		
		htmlCleaner = new HtmlCleaner(cleanerProperties);
		domSerializer = new DomSerializer(cleanerProperties);
	}

	@Override
	public List<Phrase> extractPhrases(WebPage htmlPage) {
		
		List<Phrase> phrases = new ArrayList<Phrase>();
		
		TagNode rootTag = htmlCleaner.clean(htmlPage.getPageHtml());
		
		try {
			
			Document doc = domSerializer.createDOM(rootTag);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(XPATH_EXTRACTOR, doc, XPathConstants.NODESET);
			
			this.textsFound += nodes.getLength();
			
			for (int i = 0; i < nodes.getLength(); ++i) {
				
				Node e = nodes.item(i);
				
				if(! this.isAcceptableNode(e) ) continue;
				
				for(Phrase phraseFound : this.extractPhrasesFromNode(htmlPage.getTrecID(), e)){
					
					List<Phrase> normalizedPhrases = this.normalizePhrase(phraseFound);
					
					this.acceptedPhrasesFound += normalizedPhrases.size();
					
					phrases.addAll(normalizedPhrases);
				}
				
			}
			
		} catch(DOMException e){
			System.err.println("An invalid or illegal HTML character is specified.");
			
		} catch (ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return phrases;
	}
	
	/**
	 * Dato un node verifica se questo può essere utilizzato per il calcolo delle frasi
	 * @param node
	 * @return true se accettato, false altrimenti
	 */
	public boolean isAcceptableNode(Node node){
		String tagName = "", parentTagName = "";
		
		tagName = node.getNodeName();
		if(Arrays.asList(SKIPPED_TAGS).contains(tagName)) {
			this.textsSkippedByBlacklistedTags++;
			return false;
		}
		
		try{
			parentTagName = node.getParentNode().getNodeName();
			if(tagName.equals("a") && parentTagName.equals("li")) {
				this.textsSkippedByLiATags++;
				return false;	
			}
		}catch(NullPointerException e1){
			//This exception is not relevant
		}
		
		return true;
	}
	
	/**
	 * Dato un node estra le frasi dai propri figli
	 * @param trecId
	 * @param node
	 * @return lista di frasi
	 */
	public List<Phrase> extractPhrasesFromNode(String trecId, Node node){
		
		List<Phrase> phrases = new LinkedList<Phrase>();
		
		NodeList children = node.getChildNodes();
		
		boolean concatenable = false;
		
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			
			String text = child.getNodeValue();
			
			if ( concatenable && 
					( Arrays.asList(INLINE_TAGS).contains(child.getNodeName() ) || text != null)){
				
				Phrase precPhrase = phrases.get(phrases.size() - 1);
				
				String trimmedTextContent = this.customTrim(child.getTextContent());
				
				precPhrase.setPhrase(precPhrase.getPhrase() + " " + trimmedTextContent);
				precPhrase.setTaggedPhrase(precPhrase.getTaggedPhrase() + " " + trimmedTextContent);
				
				concatenable = true;
				
			}else if (text != null){
				
				phrases.add(new Phrase(trecId, this.customTrim(text)));
				concatenable = true;
				
			}else{
				
				// it is a tag like <br /> or <img /> 
				concatenable = false;
				
			}
			
		}
		
		return phrases;
	}
	
	/**
	 * Data una singola frase restituisce un elenco di frasi ottenute eseguendo lo split
	 * @param phrase
	 * @return elenco di frasi
	 */
	public List<Phrase> normalizePhrase(Phrase phrase){
		
		List<Phrase> phrases = new LinkedList<Phrase>();
		
		phrase.setPhrase(phrase.getPhrase().replaceAll(REGEX_BLANKS, " "));
		
		
		for(String t : phrase.getPhrase().split(PUNCTUATION)){
			
			if(t.length() < MIN_CHARS) {
				this.phrasesSkippedByFewChars++;
				continue;
			}
			
			if(t.split("\\s").length < MIN_WORDS) {
				this.phrasesSkippedByFewWords++;
				continue;
			}
			
			phrases.add(new Phrase(phrase.getTrecID(), t));
		}
		
		return phrases;
		
	}
	
	/**
	 * Stampa in console il nodo passato
	 * @param node
	 */
	public void printNode(Node node){
		StringWriter writer = new StringWriter();
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(node), new StreamResult(writer));			 
		} catch (TransformerFactoryConfigurationError | TransformerException e2) {
			e2.printStackTrace();
		}
		
		System.out.println(node.getNodeName() + "|" + writer.toString());
	}
	
	/**
	 * Restituisce stringa contenente le statistiche dell'oggetto
	 */
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
	
	public String customTrim(String text){
		return text.replaceAll(REGEX_TRIM, "");
	}
}
