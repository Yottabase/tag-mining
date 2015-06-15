package org.yottabase.tagmining.tagminer;

public class UrlTagMiner extends RegexTagMiner{
	
	private static final String TAG_NAME = "URL";
	
	private static final String REGEX_TEMPLATE = "(((http(s)?|ftp)://(www\\.)?)|(((http(s)?|ftp)://)?www\\.))(([a-z])*(\\d)*)*((\\.)([a-z]{1,3})){1,2}(/)?";
	
	
	public UrlTagMiner(){
		
		super(TAG_NAME, REGEX_TEMPLATE);
		
	}
}
