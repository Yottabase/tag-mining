package org.yottabase.tagmining.tagminer;

import java.util.LinkedList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate implements InterfaceTagMiner {

	private List<RegexTagMiner> tagMiners = new LinkedList<RegexTagMiner>();
	
	public TagMinerAggregate() {
		RegexTagMiner dateMiner = new RegexTagMiner(RegexCollection.REGEX_DATE, TagCollection.TAG_DATE);
		RegexTagMiner mailMiner = new RegexTagMiner(RegexCollection.REGEX_MAIL, TagCollection.TAG_MAIL);
		RegexTagMiner ipMiner = new RegexTagMiner(RegexCollection.REGEX_IP, TagCollection.TAG_IP);
		RegexTagMiner phonepUSAMiner = new RegexTagMiner(RegexCollection.REGEX_PHONENUMBER_USA, TagCollection.TAG_PHONENUMBER);
		RegexTagMiner timeMiner = new RegexTagMiner(RegexCollection.REGEX_TIME, TagCollection.TAG_TIME);
		RegexTagMiner moneyMiner = new RegexTagMiner(RegexCollection.REGEX_MONEY, TagCollection.TAG_MONEY);
		RegexTagMiner unitMiner = new RegexTagMiner(RegexCollection.REGEX_UNIT, TagCollection.TAG_UNIT);
		RegexTagMiner urlMiner = new RegexTagMiner(RegexCollection.REGEX_URL, TagCollection.TAG_URL);

		
//		tagMiners.add( dateMiner );
//		tagMiners.add( mailMiner );
//		tagMiners.add( phonepUSAMiner );
//		tagMiners.add( ipMiner );
		tagMiners.add( timeMiner );
//		tagMiners.add( moneyMiner );
//		tagMiners.add( unitMiner );
//		tagMiners.add( urlMiner );
		
	}

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for(RegexTagMiner tagMiner : tagMiners){
			
			phrase = tagMiner.tagPhrase(phrase);
			
		}
		
		return phrase;
	}

}
