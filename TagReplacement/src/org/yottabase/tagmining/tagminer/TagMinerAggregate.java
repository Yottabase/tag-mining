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
		TagMiner phonepUSAMiner = new TagMiner(RegExCollection.REGEX_PHONENUMBER_USA, TagCollection.TAG_PHONENUMBER);
		TagMiner timeMiner = new TagMiner(RegExCollection.REGEX_TIME, TagCollection.TAG_TIME);
		TagMiner moneyMiner = new TagMiner(RegExCollection.REGEX_MONEY, TagCollection.TAG_MONEY);
		TagMiner unitMiner = new TagMiner(RegExCollection.REGEX_UNIT, TagCollection.TAG_UNIT);
		
		tagMiners.add( dateMiner );
		tagMiners.add( mailMiner );
		tagMiners.add( phonepUSAMiner );
		tagMiners.add( ipMiner );
		tagMiners.add( timeMiner );
		tagMiners.add( moneyMiner );
		tagMiners.add( unitMiner );
		
	}

	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for(TagMiner tagMiner : tagMiners){
			
			phrase = tagMiner.tagPhrase(phrase);
			
		}
		
		return phrase;
	}

}
