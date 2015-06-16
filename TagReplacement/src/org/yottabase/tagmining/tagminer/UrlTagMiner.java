package org.yottabase.tagmining.tagminer;

public class UrlTagMiner extends RegexTagMiner{
	
	private final String TAG_NAME = "URL";
	
	private final String DOMAINS = "domains";
	
	private static final String REGEX_TEMPLATE = "(((http(s)?|ftp)://)[^\\s!,;:]+)|(((http(s)?|ftp)://)?(\\w+\\.)+(%s)/[^\\s!,;:]+)";
	
	public UrlTagMiner(){
		String values = super.properties.get(DOMAINS);
		String urlRegex = String.format(REGEX_TEMPLATE, values);
		
		super.setTag(TAG_NAME);
		super.setRegex(urlRegex);
				
	}
}
