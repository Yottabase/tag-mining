package org.yottabase.tagmining.tagminer;

import java.util.LinkedList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;

public class TagMinerAggregate implements InterfaceTagMiner {

	private List<InterfaceTagMiner> tagMiners = new LinkedList<InterfaceTagMiner>();
	
	public TagMinerAggregate() {
		 
		tagMiners.add( new MoneyTagMiner() );
		tagMiners.add( new UrlTagMiner() );
		tagMiners.add( new MailTagMiner() );
		tagMiners.add( new DomainTagMiner() );
		tagMiners.add( new IpAddressTagMiner() );
		tagMiners.add( new MacAddressTagMiner() );
		tagMiners.add( new PhoneTagMiner() );
		tagMiners.add( new DateTagMiner() );
		tagMiners.add( new TimeTagMiner() );
		tagMiners.add( new UnitTagMiner() );
		
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
