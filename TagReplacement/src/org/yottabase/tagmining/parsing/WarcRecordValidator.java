package org.yottabase.tagmining.parsing;

import org.jwat.warc.WarcRecord;

public class WarcRecordValidator implements InterfaceValidator {

	@Override
	public boolean validate(Object obj) {
		WarcRecord record = null;
		
		if (obj instanceof WarcRecord)
			record = (WarcRecord) obj;
		
		try {
			
			return (record.header.warcTypeStr.equals("response") &&
					record.getPayload().getHttpHeader().contentType.contains("text/html") &&
					record.getPayloadContent() != null);
			
		} catch (Exception e) {
			return false;
		}
		
	}

}
