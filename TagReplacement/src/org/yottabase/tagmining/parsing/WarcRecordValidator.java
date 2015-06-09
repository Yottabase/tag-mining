package org.yottabase.tagmining.parsing;

import java.io.InputStream;

import org.jwat.common.HttpHeader;
import org.jwat.common.Payload;
import org.jwat.warc.WarcHeader;
import org.jwat.warc.WarcRecord;

public class WarcRecordValidator implements InterfaceValidator {

	@Override
	public boolean validate(Object obj) {
		WarcRecord record = null;
		boolean validRecord = false;

		if (obj instanceof WarcRecord)
			record = (WarcRecord) obj;
		else
			throw new ClassCastException();

		if (record != null) {
			/* Check whether the record is a response */
			WarcHeader warcHeader = record.header;

			if (warcHeader != null) {
				String warcType = warcHeader.warcTypeStr;

				if (warcType.equals("response")) {
					/* Check whether the ContentType HTTP is 'text/html' */
					Payload payload = record.getPayload();

					if (payload != null) {
						HttpHeader httpHeader = payload.getHttpHeader();

						if (httpHeader != null) {
							String headerContentType = httpHeader.contentType;

							if (headerContentType != null && headerContentType.contains("text/html")) {
								/* Check whether an input stream for the content is available */
								InputStream payloadContent = record.getPayloadContent();

								if (payloadContent != null) {
									/* The record has all the required fields and values */
									validRecord = true;
								}
							}
						}
					}
				}
			}
		}

		return validRecord;
	}

}
