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
		
		processFile(properties.get(PROP_INPUT), properties.get(PROP_OUTPUT), "00");
		
	}
	
	public static void processFile(String inputFile, String outputFolder, String fileId) throws IOException{
		InterfaceInputManager inputManager = new InputManager(inputFile);
		InterfacePhraseExtractor phraseExtractor = new PhraseExtractor();
		TagMinerAggregate tagMiner = new TagMinerAggregate();
		
		OutputManager outputWriter = new OutputManager(outputFolder, fileId);
		
		
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
