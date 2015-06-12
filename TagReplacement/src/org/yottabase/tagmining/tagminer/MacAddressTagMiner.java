package org.yottabase.tagmining.tagminer;

public class MacAddressTagMiner extends RegexTagMiner {
	
	private final String TAG_NAME = "MAC_ADDRESS";
	
	private final String regexTemplate = "(([0-9A-Fa-f]{2}[-:]){5}[0-9A-Fa-f]{2})|(([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4})";
	
	public MacAddressTagMiner() {
		super();
		
		super.setTag(TAG_NAME);
		super.setRegex(regexTemplate);
		
	}

}
