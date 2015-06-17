package org.yottabase.tagmining.tagminer;

import java.util.ArrayList;
import java.util.List;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.utils.Timer;

public class TagMinerAggregate implements InterfaceTagMiner {

	private List<InterfaceTagMiner> tagMiners = new ArrayList<InterfaceTagMiner>();
	
	private List<Timer> timers = new ArrayList<Timer>();
	
	private String[] tagMinersName = {
		"org.yottabase.tagmining.tagminer.MoneyTagMiner",
		"org.yottabase.tagmining.tagminer.UrlTagMiner",
		"org.yottabase.tagmining.tagminer.DomainTagMiner",
		"org.yottabase.tagmining.tagminer.MailTagMiner",
		"org.yottabase.tagmining.tagminer.IpAddressTagMiner",
		"org.yottabase.tagmining.tagminer.MacAddressTagMiner",
		"org.yottabase.tagmining.tagminer.PhoneTagMiner",
		"org.yottabase.tagmining.tagminer.DateTagMiner",
		"org.yottabase.tagmining.tagminer.TimeTagMiner",
		"org.yottabase.tagmining.tagminer.UnitTagMiner",
	};
	
	public TagMinerAggregate() {
		for(String name : tagMinersName){
			try {
				InterfaceTagMiner tagMiner = (InterfaceTagMiner) Class.forName(name).newInstance();
				Timer timer = new Timer(name);
				
				tagMiners.add(tagMiner);
				timers.add(timer);
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
		
		Phrase phrase = originalPhrase;
		
		for (int i = 0; i < tagMiners.size(); i++) {
			
			Timer timer = timers.get(i);
			InterfaceTagMiner tagMiner = tagMiners.get(i);
					
			timer.startOrRestart();
			phrase = tagMiner.tagPhrase(phrase);
			timer.pause();
		}
		
		return phrase;
	}
	
	public String getStats(){
		String stats = "";
		
		for(Timer timer : this.timers){
			stats += timer.toString() + "\n";
		}
		
		return stats;
	}

}
