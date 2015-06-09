package org.yottabase.tagmining.tagminer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RegularExpressionCollection{
	
	private Properties regularExpressions = new Properties();
	
	public RegularExpressionCollection() {
		String propFileName = "ereg.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			try {
				regularExpressions.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected String get(String eregName){
		
		return this.regularExpressions.getProperty(eregName);
		
	}

	
}
