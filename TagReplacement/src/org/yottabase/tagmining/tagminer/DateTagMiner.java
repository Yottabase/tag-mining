package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;

public class DateTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "TIME";
	
	//#Dec 12th 2008	Jan 14, 2009	September 22, 2008
	private static final String DATE1 = "((((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?))\\s?[\\d]{2}-?(th)?,?\\s(19[\\d]{2}|2[\\d]{3}),?))";

	//#September 22-07	September 07-20
	private static final String DATE2 = "((((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?))\\s?[\\d]{2}-[\\d]{2},?))";

	//#12th Dec|dec 2008 sunday	12th Dec 2008	12th Dec 08 sunday	12 Dec 2008 sunday	12th dec 2008 sunday
	private static final String DATE3 = "([\\d]{2}(th)?\\s((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?))\\s((19[\\d]{2}|2[\\d]{3})|('?[\\d]{2}))\\s(?:sun(?:day)?|mon(?:day)?|tue(?:sday)?|wed(?:nesday)?|thu(?:rsday)?|sat(?:urday))?)";
			
			
	RegexTagMiner[] tagMiners = {
		new RegexTagMiner(TAG_NAME, DATE1),
		new RegexTagMiner(TAG_NAME, DATE2),
		new RegexTagMiner(TAG_NAME, DATE3)
	};
	
	@Override
	public Phrase tagPhrase(Phrase phrase) {
		
		for(RegexTagMiner tagMiner : this.tagMiners){
			phrase = tagMiner.tagPhrase(phrase);
		}
		
		return phrase;
	}	
}
