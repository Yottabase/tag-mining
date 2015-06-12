package org.yottabase.tagmining.tagminer;

public class IpAddressTagMiner extends RegexTagMiner{
	
	private static final String TAG_NAME = "IP_ADDRESS";
	
	private static final String REGEX_TEMPLATE = "(([2]([0-4][0-9]|[5][0-5])|[0-1]?[0-9]?[0-9])[.]){3}(([2]([0-4][0-9]|[5][0-5])|[0-1]?[0-9]?[0-9]))";
	
	
	public IpAddressTagMiner(){
		
		super(TAG_NAME, REGEX_TEMPLATE);
		
	}
}
