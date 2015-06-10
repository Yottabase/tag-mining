package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class DateTagMiner implements InterfaceTagMiner{
	
	private static final String TAG_NAME = "DATE";

	private RegularExpressionCollection eregCollection = new RegularExpressionCollection();
	
	@Override

	public Phrase tagPhrase(Phrase phrase) {

		System.out.println(eregCollection.get("expr.date"));
		System.out.println(eregCollection.get("expr.number"));

		String regex = eregCollection.get("expr.number");

		// Create a Pattern object
		Pattern r = Pattern.compile(regex);

		// Now create matcher object.
		Matcher m = r.matcher(phrase.getPhrase());

		for (int i = 0; i < m.groupCount(); i++) {
			if (m.find()) {
				System.out.println("Found value: " + m.group(i));
			} else {
				System.out.println("NO MATCH");
			}
		}

		phrase.setTaggedPhrase("ciao #DATE");
		phrase.addTaggedWord(new TaggedWord("12-mag-05", TAG_NAME));

		return phrase;
	}

}
