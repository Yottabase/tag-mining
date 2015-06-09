package org.yottabase.tagmining.tagminer;

import java.util.ArrayList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate implements InterfaceTagMiner{

	private List<InterfaceTagMiner> tagMiners = new ArrayList<InterfaceTagMiner>();
	
	public TagMinerAggregate() {
		
		tagMiners.add(new DateTagMiner());
		
	}

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for(InterfaceTagMiner tagMiner : tagMiners){
			
			if(phrase.isTagged()) break;
			
			phrase = tagMiner.tagPhrase(phrase);
			
		}
		
		return phrase;
	}

}
