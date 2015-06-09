package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;

public class DateTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "DATE";

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
	
		
		//TODO add logica
		
		
		Phrase phrase = (Phrase) originalPhrase.clone();
		
		phrase.setTaggedPhrase("bla bla bla");
		phrase.setTag(TAG_NAME);
		
		return phrase;
	}

}
