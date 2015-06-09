package org.yottabase.tagmining.parsing;

import org.jwat.warc.WarcRecord;

public interface InterfaceParser {
	
	public WarcRecord getNextRecord();
	
	public void close();

}
