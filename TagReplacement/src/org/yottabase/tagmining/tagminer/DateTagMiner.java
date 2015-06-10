package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;

public class DateTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "DATE";

	private RegularExpressionCollection eregCollection = new RegularExpressionCollection();
	
	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {

		
	      String regex = eregCollection.get("expr.number");

	      // Create a Pattern object
	      Pattern r = Pattern.compile(regex);

	      // Now create matcher object.
	      Matcher m = r.matcher(originalPhrase.getPhrase());
	     
	      for (int i = 0; i < m.groupCount(); i++) {
	    	  if (m.find( )) { 
	    		  System.out.println("Found value: " + m.group(i) );
	    	  }else {
	 	         System.out.println("NO MATCH");
		      }
	      }
	    
		
		Phrase phrase = (Phrase) originalPhrase.clone();
		
		phrase.setTaggedPhrase("bla bla bla");
		phrase.setTag(TAG_NAME);
		
		return phrase;
	}

}
