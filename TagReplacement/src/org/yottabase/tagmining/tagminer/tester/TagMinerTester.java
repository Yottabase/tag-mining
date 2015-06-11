package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		
		Phrase phrase = new Phrase("00003", "ciao@bello.com ciao 20m 15s   (800)-555-2468     10h 20m 30s  ale@gmail.com");
		
		TagMinerAggregate tagMiner = new TagMinerAggregate();

		Phrase taggedPhrase = tagMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
		
}
