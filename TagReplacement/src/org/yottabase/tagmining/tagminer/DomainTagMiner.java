package org.yottabase.tagmining.tagminer;

public class DomainTagMiner extends RegexTagMiner{
	
	private static final String PROP = "domains";

	private static final String TAG_NAME = "DOMAIN";
	
	private static final String REGEX_TEMPLATE = "(\\w+\\.)+(%s)\\b";
	
	
	public DomainTagMiner(){
		
		String domains = super.properties.get(PROP);
		String values = (domains).replaceAll(",", "|");
		
		this.setTag(TAG_NAME);
		super.setRegex(String.format(REGEX_TEMPLATE, values));
		
	}

}
