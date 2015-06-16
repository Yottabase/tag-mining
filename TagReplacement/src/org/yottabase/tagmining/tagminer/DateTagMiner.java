package org.yottabase.tagmining.tagminer;

public class DateTagMiner extends RegexTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "DATE";
	
	private final String MONTHS = "month";
	
	//#Dec 12th 2008	Jan 14, 2009	September 22, 2008
	private String DATE1 = "(%s)\\s?(([0-2]?[1-9])|([3][0-1]))-?(th)?,?\\s[1-2][\\d]{3}";
	
	//#September 22-07	Sep 31-07		September 31-12 	September 07/31		September 07-31
	private String DATE2 = "((%s)\\s?(([0-2]?[1-9]|([3][0-1]))(-|\\/)(([0][1-9])|([1][0-2]))))";

	//#12th Dec|dec 2008 sunday	12th Dec 2008	12th Dec 08 sunday	12 Dec 2008 sunday	12th dec 2008 sunday	12-Dec-2008-sunday		12.Dec.2008.sunday	12Dec2008 sunday
	private String DATE3 = "((([0-2]?[1-9])|([3][0-1]))(th)?(\\s|-|\\.)?((%s))(\\s|-|\\.)?((19[\\d]{2}|2[\\d]{3})|('?[\\d]{2}))((\\s|-|\\.)(?:sun(?:day)?|mon(?:day)?|tue(?:sday)?|wed(?:nesday)?|thu(?:rsday)?|sat(?:urday)))?)";
		
	//25\12\2009	25.12.2009		25.12.1909	25-09-1509 	24/4/1200 	2/4/2015
	private final String DATE4 = "((([0-2]?[1-9])|([3][0-1]))(\\\\|\\.|-|\\/)((0?[1-9])|(1[1-2]))(\\\\|\\.|-|\\/)((1[\\d]{3}|2[\\d]{3})))";	

	//2006-06-19	2006/06/19	2006\06\19		2006.06.19		2006.1.1
	private final String DATE5 = "(((1|2)[\\d]{3}))(-|\\.|/|\\\\)((0?[1-9]|1[1-2]))(-|\\.|/|\\\\)((3[0-1])|([0-2]?[1-9]))";	

	public DateTagMiner() {
		super();
		String values = (super.properties.get(MONTHS)).replaceAll(",", "|");
		String dateRegex1 = String.format(this.DATE1, values);
		String dateRegex2 = String.format(this.DATE2, values);
		String dateRegex3 = String.format(this.DATE3, values);
		String dateRegex4 = this.DATE4;
		String dateRegex5 = this.DATE5;
		String dateRegex = dateRegex1 + "|" + dateRegex2 + "|" + dateRegex3 + "|" + dateRegex4 + "|" + dateRegex5;
		
		super.setTag(TAG_NAME);
		super.setRegex(dateRegex);
		
	}
	
}
