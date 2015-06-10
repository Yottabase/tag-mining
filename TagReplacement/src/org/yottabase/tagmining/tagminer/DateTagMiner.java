package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class DateTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "DATE";

	private RegularExpressionCollection eregCollection = new RegularExpressionCollection();
	
	@Override
	public Phrase tagPhrase(Phrase phrase) {
	
		System.out.println(eregCollection.get("expr.date"));
		System.out.println(eregCollection.get("expr.number"));
		
		//TODO add logica
		
		phrase.setTaggedPhrase("ciao #DATE");
		phrase.addTaggedWord( new TaggedWord("12-mag-05", TAG_NAME) );
		
		return phrase;
	}

}
