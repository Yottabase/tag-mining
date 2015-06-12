package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.InterfaceTagMiner;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		InterfaceTagMiner dateMiner = new TagMinerAggregate();
		
		Phrase phrase = new Phrase("00003", "ciao 12-mag-05 l'oggetto costa 50.3 usd ma solo per oggi, domani costa £5");
		
		
		Phrase taggedPhrase = dateMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
	
	
	
}
