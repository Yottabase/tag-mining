package org.yottabase.tagmining.tagminer;

import org.yottabase.tagmining.core.PropertyReader;

public class RegexCollection {
	
	private final static String PROPERTY_FILE_NAME = "ereg.properties";
	
	private static PropertyReader property = new PropertyReader(PROPERTY_FILE_NAME);
	
	
	public final static String REGEX_DATE = property.get("expr.date");
	
	public final static String REGEX_IP = property.get("expr.ip");
	
	public final static String REGEX_MAIL = property.get("expr.mail");
	public final static String REGEX_TIME = property.get("expr.time");
	
	public final static String REGEX_PHONENUMBER_ITA = property.get("expr.phone.ita");
	public final static String REGEX_PHONENUMBER_USA = property.get("expr.phone.usa");
	
//	public final static String REGEX_ZIPCODE = "";
	
	public final static String REGEX_UNIT = property.get("expr.unit");
	
	public final static String REGEX_MONEY = property.get("expr.money");

	public static final String REGEX_URL = property.get("expr.url");
	
}
