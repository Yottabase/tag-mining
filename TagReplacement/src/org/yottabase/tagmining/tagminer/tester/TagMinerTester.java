package org.yottabase.tagmining.tagminer.tester;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.tagminer.DateTagMiner;
import org.yottabase.tagmining.tagminer.InterfaceTagMiner;
import org.yottabase.tagmining.tagminer.MailTagMiner;
import org.yottabase.tagmining.tagminer.TagMinerAggregate;

public class TagMinerTester {

	
	public static void main(String[] args) {
		
		//InterfaceTagMiner tagMiner = new DateTagMiner();
		
		InterfaceTagMiner tagMiner = new MailTagMiner();
		
		//InterfaceTagMiner tagMiner = new TagMinerAggregate();
		
		Phrase phrase = new Phrase("00003", "ciao@bello.com ciao 20m 15s");
		//Phrase phrase = new Phrase("00003", "foo@demo.net");

		Phrase taggedPhrase = tagMiner.tagPhrase(phrase);
		
		System.out.println(taggedPhrase);
		
	}
	
	
	
}
