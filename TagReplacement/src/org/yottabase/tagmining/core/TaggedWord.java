package org.yottabase.tagmining.core;

public class TaggedWord {

	private String word;
	
	private String tag;

	public TaggedWord(String word, String tag) {
		super();
		this.word = word;
		this.tag = tag;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "TaggedWord [word=" + word + ", tag=" + tag + "]";
	}
	
}
