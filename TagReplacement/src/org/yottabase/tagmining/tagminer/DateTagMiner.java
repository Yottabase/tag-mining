package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.Phrase;

public class DateTagMiner extends RegexTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "TIME";
	
	private final String MONTHS = "months";
	//#Dec 12th 2008	Jan 14, 2009	September 22, 2008
	private static final String DATE1 = "((((%s))\\s?(([0-2][1-9])|([3][0-1]))-?(th)?,?\\s(19[\\d]{2}|2[\\d]{3}),?))";

	//#September 22-07	Sep 31-07		September 31-12 	September 07/31		September 07-31
	private static final String DATE2 = "((((%s))\\s?(([0-2][1-9]|([3][0-1]))(-|\\/)(([0][1-9])|([1][0-2])))))";

	//#12th Dec|dec 2008 sunday	12th Dec 2008	12th Dec 08 sunday	12 Dec 2008 sunday	12th dec 2008 sunday
	private static final String DATE3 = "((([0-2][1-9])|([3][0-1]))(th)?\\s((%s))\\s((19[\\d]{2}|2[\\d]{3})|('?[\\d]{2}))\\s(?:sun(?:day)?|mon(?:day)?|tue(?:sday)?|wed(?:nesday)?|thu(?:rsday)?|sat(?:urday))?)";
		
	//25\12\2009	25.12.2009		25.12.1909		25.09.1509
	private static final String DATE4 = "((([0-2][1-9])|([3][0-1]))(\\\\|\\.)((0?[1-9])|(1[1-2]))(\\\\|\\.)((1[\\d]{3}|2[\\d]{3})))";
			
	RegexTagMiner[] tagMiners = {
		new RegexTagMiner(TAG_NAME, DATE1),
//		new RegexTagMiner(TAG_NAME, DATE2),
//		new RegexTagMiner(TAG_NAME, DATE3),
//		new RegexTagMiner(TAG_NAME, DATE4)
	};
	
	public DateTagMiner() {
		super();
		
		String values = (super.properties.get(MONTHS)).replaceAll(",", "|");
		
		super.setTag(TAG_NAME);
		super.setRegex(String.format(this.DATE1, values));
		super.setRegex(String.format(this.DATE2, values));
		super.setRegex(String.format(this.DATE3, values));
		super.setRegex(String.format(this.DATE4, values));
		
	}
	
	@Override
	public Phrase tagPhrase(Phrase phrase) {
		
		for(RegexTagMiner tagMiner : this.tagMiners){
			phrase = tagMiner.tagPhrase(phrase);
		}
		
		return phrase;
	}	
}
