package org.yottabase.tagmining.phraseextractor;

import java.util.List;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.WebPage;

public interface InterfacePhraseExtractor {

	public List<Phrase> extractPhrases(WebPage htmlPage);
	
	public String getStats();
	
}
