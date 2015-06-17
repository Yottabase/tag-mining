package org.yottabase.tagmining.tagminer;

public class DateTagMiner extends RegexTagMiner implements InterfaceTagMiner {

	private static final String TAG_NAME = "DATE";
	
	private final String MONTHS = "month";
	
	/*mm-gg-hh(hh)?
	 * 	Dec 12th 2008	Jan 14, 2009	September 22, 2008	September 22-07	Sep 31/07
	 */
	private String DATE1 = "(%s)\\s?(([0-2]?[1-9])|(3[0-1]))(fs|nd|rd|th)?(-|,|\\/)?\\s?[0-2]\\d{1,3}";
	
	/*hhhh-.mm.-gg
	 *	2006-06-19	2006/06/19	2006.06.19		2006.6.19
	 */
	private final String DATE2 = "([1-2][\\d]{3})(-|\\.|/)(0?[1-9]|1[1-2])(-|\\.|/)((3[0-1])|([0-2]?[1-9]))";	
	
	/*25.12.2009	25-09-1509 	24/4/1200 	2/4/2015 	12-12-08
	 *12th Dec 2008	12 Dec 2008	12-Dec-2008	12/Dec/2008 12.Dec.2008	12Dec2008
	 */
	private String DATE3 = "\\b(([0-2]?[1-9])|([3][0-1]))(fs|nd|rd|th)?(((\\.|-|\\/| ?)(%s)(\\.|-|\\/| )?)|(\\.|-|\\/)(((0?[1-9])|(1[1-2]))(\\.|-|\\/| )))[0-2]+[\\d]{1,3}";
	
	public DateTagMiner() {
		super();
		String values = (super.properties.get(MONTHS)).replaceAll(",", "|");
		String dateRegex1 = String.format(this.DATE1, values);
		String dateRegex3 = String.format(this.DATE3, values);

		String dateRegex2 = this.DATE2;

		String dateRegex = dateRegex1 + "|" + dateRegex2 + "|" + dateRegex3;
		
		super.setTag(TAG_NAME);
		super.setRegex(dateRegex);
		
	}
	
}
