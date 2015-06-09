package org.yottabase.tagmining;

import java.util.List;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.WebPage;
import org.yottabase.tagmining.parsing.InterfaceInputManager;
import org.yottabase.tagmining.parsing.InputManager;
import org.yottabase.tagmining.phraseextractor.InterfacePhraseExtractor;
import org.yottabase.tagmining.phraseextractor.PhraseExtractorImpl;
import org.yottabase.tagmining.tagminer.InterfaceTagMiner;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class Main {

	public static void main(String[] args) {
		
		InterfaceInputManager inputManager = new InputManager("/filename");
		InterfacePhraseExtractor phraseExtractor = new PhraseExtractorImpl();
		InterfaceTagMiner tagMiner = new TagMinerAggregate();
		
		
		WebPage webPage = null;
		
		while( (webPage = inputManager.getNextWebPage()) != null ){
			
			List<Phrase> phrases = phraseExtractor.extractPhrases(webPage);
			
			for(Phrase phrase : phrases){
			
				phrase = tagMiner.tagPhrase(phrase);
			
				//TODO aggiungere salvataggio su file
				System.out.format("%s\t%s\t%s\t%s\n", 
					phrase.getTrecID(),
					phrase.getPhrase(),
					phrase.getTaggedPhrase(),
					phrase.getTag()
				);
			}
		}
	}
}
