package org.yottabase.tagmining.output;

public class TSVFormatter implements InterfaceFormatter {

	@Override
	public String format(String... params) {
		String r = "";
		
		for(String field : params) {
			r += field + "\t";
		}
		return r + "\n";
		
	}
	
}
