package org.yottabase.tagmining.tagminer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class TagMiner {

	private final String TAG;

	private final String REGEX;

	public TagMiner(final String regex, final String tag) {
		this.TAG = tag;
		this.REGEX = regex;
	}

	public Phrase tagPhrase(Phrase phrase) {
		
		// Create a Pattern object
		Pattern r = Pattern.compile(this.REGEX);

		// Now create matcher object.
		Matcher m = r.matcher(phrase.getPhrase());

		for (int i = 0; i < m.groupCount(); i++) {
			if (m.find()) {
				System.out.println("Found value: " + m.group(i));
				String taggedPhrase = phrase.getPhrase();
				phrase.setTaggedPhrase(taggedPhrase.replace(m.group(i), "#" + this.TAG));
				phrase.addTaggedWord(new TaggedWord(m.group(i), this.TAG));
			}
		}

		return phrase;
	}

}
