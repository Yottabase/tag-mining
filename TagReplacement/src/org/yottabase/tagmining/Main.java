package org.yottabase.tagmining;

import java.io.File;
import java.io.FilenameFilter;
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
import org.yottabase.tagmining.utils.Timer;

public class Main {
	
	private final static String INPUT_FILE_EXT = ".warc";

	private final static String PROP_INPUT = "dataset.input";
	
	private final static String PROP_OUTPUT = "dataset.output";
	
	private final static String CONFIG_PROPERTIES = "config.properties";

	public static void main(String[] args) throws IOException {
		PropertyReader properties = new PropertyReader(CONFIG_PROPERTIES);
		String inputPath = properties.get(PROP_INPUT);
		String outputPath = properties.get(PROP_OUTPUT);
		
		File[] files = (new File(inputPath).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(INPUT_FILE_EXT);
			}
		}));
		
		for (File file : files) {
			String filePath = file.getPath();
			String fileName = file.getName();
			String[] fileNameSplit = fileName.split("-");
			String fileId = (fileNameSplit.length != 0) ? fileNameSplit[3] : fileName.split(".")[0];
			
			processFile(filePath, outputPath, fileId);
			
		}
		
	}
	
	public static void processFile(String inputFile, String outputFolder, String fileId) throws IOException{
		Timer timerExtraction = new Timer();
		Timer timerTagMining = new Timer();
		
		InterfaceInputManager inputManager = new InputManager(inputFile);
		InterfacePhraseExtractor phraseExtractor = new PhraseExtractor();
		TagMinerAggregate tagMiner = new TagMinerAggregate();
		
		OutputManager outputWriter = new OutputManager(outputFolder, fileId);
		
		
		WebPage webPage = null;
		
		while( (webPage = inputManager.getNextWebPage()) != null ){
			
			timerExtraction.startOrRestart();
			List<Phrase> phrases = phraseExtractor.extractPhrases(webPage);
			timerExtraction.pause();
			
			for(Phrase phrase : phrases){
			
				timerTagMining.startOrRestart();
				phrase = tagMiner.tagPhrase(phrase);
				timerTagMining.pause();
			
				outputWriter.writePhrase(phrase);
			}
			
		}
		
		System.out.println(phraseExtractor.getStats());
		System.out.println("Extraction elapsed time: " + timerExtraction.getElapsedTime() + " ms");
		System.out.println("Tagging elapsed time: " + timerTagMining.getElapsedTime() + " ms");
	}
}
