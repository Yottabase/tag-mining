package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.RegExCollection;
import org.yottabase.tagmining.tagminer.TagCollection;
import org.yottabase.tagmining.tagminer.TagMiner;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		//InterfaceTagMiner tagMiner = new DateTagMiner();
		TagMiner dateMiner = new TagMiner(RegExCollection.REGEX_MAIL, TagCollection.TAG_MAIL);
		
		//InterfaceTagMiner tagMiner = new TagMinerAggregate();
		
		Phrase phrase = new Phrase("00003", "ciao@bello.com ciao 20m 15s");
		//Phrase phrase = new Phrase("00003", "foo@demo.net");

		Phrase taggedPhrase = dateMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
	
	
	
}
