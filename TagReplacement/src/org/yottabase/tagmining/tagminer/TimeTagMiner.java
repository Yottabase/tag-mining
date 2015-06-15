package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;

public class TimeTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "TIME";
	
	//	12h:12m:12s		22h 12m 12s		12h12m12s
	private static final String TIME1 = "(([0-1]?\\d|2?[0-4])h)(\\s|:)(([0-5]?\\d)m)(\\s|:)([0-5]?\\d)s";

	//am	pm	05:00 pm  	00:00 PM	12:59:00PM 		1-01-00 PM		1.01.00 PM
	private static final String TIME2 = "(((0?\\d)|(1[0-2]))((:|-|\\.)([0-5]?\\d)){1,2}(\\s?)(AM|PM))";
	
	//h24	23:00:00	05:00
	private static final String TIME3 = "(([0-1]?\\d|[2]?[0-4])((:|\\.|-)([0-5]\\d)){1,2})\\b";

	//10:27PM	 1:31	20:34pm		20:34 pm
	private static final String TIME4 = "[\\d]{1,2}:[\\d]{2}(:[\\d]{2})?(\\s)?(AM|PM)?";
	
	RegexTagMiner[] tagMiners = {
		new RegexTagMiner(TAG_NAME, TIME1),
		new RegexTagMiner(TAG_NAME, TIME2),
		new RegexTagMiner(TAG_NAME, TIME3),
		new RegexTagMiner(TAG_NAME, TIME4)
	};
	
	@Override
	public Phrase tagPhrase(Phrase phrase) {
		
		for(RegexTagMiner tagMiner : this.tagMiners){
			phrase = tagMiner.tagPhrase(phrase);
		}
		
		return phrase;
	}	
}
