package org.yottabase.tagmining.parsing;

import org.yottabase.tagmining.core.WebPage;

public interface InterfaceInputManager {
	
	/**
	 * Restituisce la prossima WebPage se presente nel complesso dei file .warc di input,
	 * null altrimenti.
	 * 
	 * @param webpage
	 */
	public WebPage getNextWebPage();

}
