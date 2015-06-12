package org.yottabase.tagmining.tagminer;

public class MailTagMiner extends RegexTagMiner{
	
	public MailTagMiner(){
		
		this.setRegex("[a-zA-Z0-9-]{1,}@([a-zA-Z\\.])?[a-zA-Z]{1,}\\.[a-zA-Z]{1,4}");
		this.setTag("MAIL");
		
	}

}
