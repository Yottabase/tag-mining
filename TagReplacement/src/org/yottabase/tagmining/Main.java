package org.yottabase.tagmining;

import java.io.IOException;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.PropertyReader;
import org.yottabase.tagmining.core.WebPage;
import org.yottabase.tagmining.output.OutputManager;
import org.yottabase.tagmining.parsing.InterfaceInputManager;
import org.yottabase.tagmining.parsing.InputManager;
import org.yottabase.tagmining.phraseextractor.InterfacePhraseExtractor;
import org.yottabase.tagmining.phraseextractor.PhraseExtractor;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class Main {
	
	private final static String CONFIG_PROPERTIES = "config.properties";

	private final static String PROP_INPUT = "dataset.input";
	
	private final static String PROP_OUTPUT = "dataset.output";

	public static void main(String[] args) throws IOException {
		
		PropertyReader properties = new PropertyReader(CONFIG_PROPERTIES);
		InterfaceInputManager inputManager = new InputManager(properties.get(PROP_INPUT));
		InterfacePhraseExtractor phraseExtractor = new PhraseExtractor();
		TagMinerAggregate tagMiner = new TagMinerAggregate();
		
		OutputManager outputWriter = new OutputManager(properties.get(PROP_OUTPUT), "test");
		
		
		WebPage webPage = null;
		
		while( (webPage = inputManager.getNextWebPage()) != null ){
			
			List<Phrase> phrases = phraseExtractor.extractPhrases(webPage);
			
			for(Phrase phrase : phrases){
			
				phrase = tagMiner.tagPhrase(phrase);
			
				outputWriter.writePhrase(phrase);
			}
		}
	}
}
