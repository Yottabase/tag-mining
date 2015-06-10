package org.yottabase.tagmining.tagminer;

import java.util.LinkedList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate {

	private List<TagMiner> tagMiners = new LinkedList<TagMiner>();
	
	public TagMinerAggregate() {
		TagMiner dateMiner = new TagMiner(RegExCollection.REGEX_DATE, TagCollection.TAG_DATE);
		TagMiner mailMiner = new TagMiner(RegExCollection.REGEX_MAIL, TagCollection.TAG_MAIL);
		TagMiner ipMiner = new TagMiner(RegExCollection.REGEX_IP, TagCollection.TAG_IP);
		
		tagMiners.add( dateMiner );
		tagMiners.add( mailMiner );
		tagMiners.add( ipMiner );
		
	}

	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for(TagMiner tagMiner : tagMiners){
			
			phrase = tagMiner.tagPhrase(phrase);
			
		}
		
		return phrase;
	}

}
