package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class IpTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "IP";

	private RegularExpressionCollection eregCollection = new RegularExpressionCollection();
	
	@Override

	public Phrase tagPhrase(Phrase phrase) {

		
		String regex = eregCollection.get("expr.ip");
		
		// Create a Pattern object
		Pattern r = Pattern.compile(regex);

		// Now create matcher object.
		Matcher m = r.matcher(phrase.getPhrase());
		
		for (int i = 0; i < m.groupCount(); i++) {
			if (m.find()) {
				System.out.println("Found value: " + m.group(i));
				String taggedPhrase= phrase.getPhrase();
				phrase.setTaggedPhrase(taggedPhrase.replace(m.group(i), "#"+TAG_NAME));
				phrase.addTaggedWord(new TaggedWord(m.group(i), TAG_NAME));
			} 
		}

		return phrase;
	}

}
