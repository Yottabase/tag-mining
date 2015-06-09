package org.yottabase.tagmining.core;

public class Phrase implements Cloneable{

	private String trecID;
	
	private String phrase;
	
	private String taggedPhrase;
	
	private String tag;

	public Phrase() {
		super();
	}

	public String getTrecID() {
		return trecID;
	}

	public void setTrecID(String trecID) {
		this.trecID = trecID;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String getTaggedPhrase() {
		return taggedPhrase;
	}

	public void setTaggedPhrase(String taggedPhrase) {
		this.taggedPhrase = taggedPhrase;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Object clone() {
        Phrase phrase = new Phrase();
        phrase.setTrecID(this.getTrecID());
		phrase.setPhrase(this.getPhrase());
		return phrase;
    }

	@Override
	public String toString() {
		return "Phrase [trecID=" + trecID + ", phrase=" + phrase
				+ ", taggedPhrase=" + taggedPhrase + ", tag=" + tag + "]";
	}
	
	
}
