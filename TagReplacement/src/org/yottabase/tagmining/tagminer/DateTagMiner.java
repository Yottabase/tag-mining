package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;

public class DateTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "DATE";

	@Override
	public Phrase tagPhrase(Phrase originalPhrase) {
		
	      String regex = "(.*)(\\d+)(.*)";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(regex);

	      // Now create matcher object.
	      Matcher m = r.matcher(originalPhrase.getPhrase());
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	      } else {
	         System.out.println("NO MATCH");
	      }
		
		
		Phrase phrase = (Phrase) originalPhrase.clone();
		
		phrase.setTaggedPhrase("bla bla bla");
		phrase.setTag(TAG_NAME);
		
		return phrase;
	}

}
