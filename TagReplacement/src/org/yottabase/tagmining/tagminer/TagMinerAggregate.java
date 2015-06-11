package org.yottabase.tagmining.tagminer;

import java.util.LinkedList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate implements InterfaceTagMiner {

	private List<RegexTagMiner> tagMiners = new LinkedList<RegexTagMiner>();
	
	public TagMinerAggregate() {
		RegexTagMiner dateMiner = new RegexTagMiner(RegexCollection.REGEX_DATE, TagCollection.TAG_DATE);
		
		tagMiners.add( dateMiner );
		
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
