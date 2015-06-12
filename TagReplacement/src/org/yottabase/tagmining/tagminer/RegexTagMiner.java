package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.PropertyReader;
import org.yottabase.tagmining.core.TaggedWord;

public class RegexTagMiner implements InterfaceTagMiner {

	private String tag;

	private String regex;
	
	private final String VALUES_PROPERTY_FILE = "values.properties";
	
	protected PropertyReader properties = new PropertyReader(VALUES_PROPERTY_FILE);

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public Phrase tagPhrase(Phrase phrase) {
		String taggedPhrase = phrase.getTaggedPhrase();
		
		// Create a Pattern object
		Pattern r = Pattern.compile(this.regex);

		// Now create matcher object.
		Matcher m = r.matcher(taggedPhrase);

		while (m.find()) {
			System.out.println("Found value: " + m.group(0));
			
			taggedPhrase = taggedPhrase.replace(m.group(0), "#" + this.tag);
			phrase.addTaggedWord(new TaggedWord(m.group(0), this.tag));
		}
		phrase.setTaggedPhrase(taggedPhrase);

		return phrase;
	}

}
