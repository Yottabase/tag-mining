package org.yottabase.tagmining.phraseextractor;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.WebPage;

public class PhraseExtractor implements InterfacePhraseExtractor {
	
	private static final String PUNCTUATION = "\\.?!;";

	@Override
	public List<Phrase> extractPhrases(WebPage htmlPage) {
		
		Document document = Jsoup.parse( htmlPage.getPageHtml() );
		List<Phrase> phrases = new ArrayList<Phrase>();
		
		Elements textEl = document.select("p, div");
		
		for(Element textNode: textEl){
			
			for(String text : textNode.text().split(PUNCTUATION)){
				
				if(text.length() == 0) continue;
				
				phrases.add(new Phrase(htmlPage.getTrecID(), text));
				
			}
			
		}
		
		return phrases;
	}

}
