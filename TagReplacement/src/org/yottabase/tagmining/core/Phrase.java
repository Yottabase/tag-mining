package org.yottabase.tagmining.core;

import java.util.ArrayList;
import java.util.List;

public class Phrase{

	private String trecID;
	
	private String phrase;
	
	private String taggedPhrase;
	
	private List<TaggedWord> taggedWords = new ArrayList<TaggedWord>();

	public Phrase() {
		
	}
	
	public Phrase(String trecID, String originalPhrase) {
		this.trecID = trecID;
		this.phrase = originalPhrase;
		this.taggedPhrase = originalPhrase;
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
	
	public List<TaggedWord> getTaggedWords() {
		return taggedWords;
	}

	public void setTaggedWords(List<TaggedWord> taggedWords) {
		this.taggedWords = taggedWords;
	}
	
	public void addTaggedWord(TaggedWord taggedWord){
		this.taggedWords.add(taggedWord);
	}
	
	@Override
	public String toString() {
		
		String str = "";
		for(TaggedWord taggedWord : taggedWords){
			str += taggedWord + " ";
		}
		
		return "Phrase [trecID=" + trecID + ", phrase=" + phrase
				+ ", taggedPhrase=" + taggedPhrase + ", taggedWords="
				+ str + "]";
	}

	public boolean isTagged(){
		return ! this.taggedWords.isEmpty();
	}
	
}
