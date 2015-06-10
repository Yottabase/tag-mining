package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.PropertyReader;

public class RegExCollection {
	
	private final static String PROPERTY_FILE_NAME = "ereg.properties";
	
	private static PropertyReader property = new PropertyReader(PROPERTY_FILE_NAME);
	
	
	public final static String REGEX_DATE = property.get("expr.date");
	
	public final static String REGEX_IP = property.get("expr.ip");
	
	public final static String REGEX_MAIL = property.get("expr.mail");
	
//	public final static String REGEX_PHONENUMBER_IT = "";
//	public final static String REGEX_PHONENUMBER_USA = "";
//	
//	public final static String REGEX_ZIPCODE = "";
//	
//	public final static String REGEX_UNIT = "";
//	
//	public final static String REGEX_MONEY = "";
	
}
