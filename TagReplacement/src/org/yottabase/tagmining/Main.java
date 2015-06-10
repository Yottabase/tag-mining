package org.yottabase.tagmining;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.yottabase.tagmining.core.Config;
import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.WebPage;
import org.yottabase.tagmining.parsing.InterfaceInputManager;
import org.yottabase.tagmining.parsing.InputManager;
import org.yottabase.tagmining.phraseextractor.InterfacePhraseExtractor;
import org.yottabase.tagmining.phraseextractor.PhraseExtractor;
import org.yottabase.tagmining.tagminer.InterfaceTagMiner;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class Main {

	private static final String PROP_INPUT = "dataset.input";

	public static void main(String[] args) throws IOException {
		
		Config config = new Config();
		Properties properties = config.getProperties();
		InterfaceInputManager inputManager = new InputManager(properties.getProperty(PROP_INPUT));
		InterfacePhraseExtractor phraseExtractor = new PhraseExtractor();
		InterfaceTagMiner tagMiner = new TagMinerAggregate();
		
		
		WebPage webPage = null;
		
		while( (webPage = inputManager.getNextWebPage()) != null ){
			
			List<Phrase> phrases = phraseExtractor.extractPhrases(webPage);
			
			for(Phrase phrase : phrases){
			
				phrase = tagMiner.tagPhrase(phrase);
			
				//TODO aggiungere salvataggio su file
				System.out.println(phrase);
			}
		}
	}
}
