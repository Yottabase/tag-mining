package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.RegExCollection;
import org.yottabase.tagmining.tagminer.TagCollection;
import org.yottabase.tagmining.tagminer.TagMiner;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		TagMiner dateMiner = new TagMiner(RegExCollection.REGEX_DATE, TagCollection.TAG_DATE);
		
		Phrase phrase = new Phrase("00003", "ciao 12-mag-05");
		
		
		Phrase taggedPhrase = dateMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
	
	
	
}
