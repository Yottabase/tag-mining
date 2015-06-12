package org.yottabase.tagmining.tagminer;

import java.util.LinkedList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate implements InterfaceTagMiner {

	private List<InterfaceTagMiner> tagMiners = new LinkedList<InterfaceTagMiner>();
	
	public TagMinerAggregate() {
		InterfaceTagMiner moneyMiner = new MoneyTagMiner();
		
		tagMiners.add( moneyMiner );
		
	}

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for(InterfaceTagMiner tagMiner : tagMiners){
			
			phrase = tagMiner.tagPhrase(phrase);
			
		}
		
		return phrase;
	}

}
