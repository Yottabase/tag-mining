package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.DateTagMiner;
import org.yottabase.tagmining.tagminer.InterfaceTagMiner;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		InterfaceTagMiner tagMiner = new DateTagMiner();
		
		Phrase phrase = new Phrase("00003", "ciao 12-mag-05");
		
		Phrase taggedPhrase = tagMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
	
	
	
}
