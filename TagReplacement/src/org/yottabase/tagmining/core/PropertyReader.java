package org.yottabase.tagmining.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties propertiesFile = new Properties();

	public PropertyReader(String propFileName) {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				propertiesFile.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String get(String eregName) {
		return this.propertiesFile.getProperty(eregName);

	}

}
