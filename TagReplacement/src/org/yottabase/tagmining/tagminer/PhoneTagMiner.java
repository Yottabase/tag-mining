package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;

public class PhoneTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "PHONE";
	
	// (800) 555 2468		(800)-555-2468
	private static final String PHONE1 = "[(]?\\d{3}[)]\\s?(-|\\d)?\\s?\\d{3}\\s?-?\\s?\\d{4}";
	
	// +39 390 4978405	+39 3904978405	+39 390-4978405		390-4978405		3904978405
	private static final String PHONE2 = "^(\\+?([0-9]{1,2})(\\s{1})([0-9]{3}(\\s|-|[0-9])([0-9]{6,7})))";
	
	RegexTagMiner[] tagMiners = {
		new RegexTagMiner(TAG_NAME, PHONE1),
		new RegexTagMiner(TAG_NAME, PHONE2)
	};
	
	@Override
	public Phrase tagPhrase(Phrase phrase) {
		
		for(RegexTagMiner tagMiner : this.tagMiners){
			phrase = tagMiner.tagPhrase(phrase);
		}
		
		return phrase;
	}	
}
