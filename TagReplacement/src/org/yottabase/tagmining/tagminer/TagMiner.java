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
		String taggedPhrase = phrase.getTaggedPhrase();
		
		// Create a Pattern object
		Pattern r = Pattern.compile(this.REGEX);

		// Now create matcher object.
		Matcher m = r.matcher(taggedPhrase);
	
		while (m.find()) {
			System.out.println( "Found value:   " + m.group(0) + "    TAG   : " + this.TAG);
			
			phrase.setTaggedPhrase( taggedPhrase.replace( m.group(0), "#" + this.TAG) );
			phrase.addTaggedWord(new TaggedWord(m.group(0), this.TAG));
		}
		
		return phrase;
	}

}
