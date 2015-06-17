package org.yottabase.tagmining.tagminer;

public class MacAddressTagMiner extends RegexTagMiner {
	
	private final String TAG_NAME = "MAC_ADDRESS";
	
	private final String regexTemplate = "\\b([^\\s!,;:_]{2}(:|-)){5}[^\\s!,;:_]{2}\\b";
	
	public MacAddressTagMiner() {
		super();
		
		super.setTag(TAG_NAME);
		super.setRegex(regexTemplate);
		
	}

}
