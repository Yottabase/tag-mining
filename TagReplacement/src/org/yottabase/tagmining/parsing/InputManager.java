package org.yottabase.tagmining.parsing;

import java.io.InputStream;
import java.util.Scanner;

import org.jwat.warc.WarcRecord;
import org.yottabase.tagmining.core.WebPage;

public class InputManager implements InterfaceInputManager {

	private InterfaceParser parser;

	private InterfaceValidator validator;

	public InputManager(String inputFilePath) {
		this.validator = new WarcRecordValidator();
		this.parser = new WarcParser(inputFilePath);
	}

	/**
	 * Restituisce la successiva WebPage estraendola dalla sorgente di input
	 * corrente se la sorgente di input corrente dispone di un ulteriore record,
	 * altrimenti la WebPage viene estratta da una successiva sorgente di input
	 * se vi sono ulterio- -ri sorgenti di input disponibili.
	 * 
	 * Se non sono presenti ulteriori record Warc (sorgenti di input terminate)
	 * viene restituito null.
	 * 
	 */
	public WebPage getNextWebPage() {
		WarcRecord record;
		WebPage page;

		do {
			page = null;

			/*
			 * Se il record è null il file è terminato dunque il parser viene
			 * chiuso
			 */
			if ((record = parser.getNextRecord()) == null) {
				parser.close();
				return null;
			}

			/* Trasforma il record in una web-page */
			if (validator.validate(record))
				page = warcRecordToWebPage(record);

			/*
			 * Esegui questi passi fin tanto che la web-page generata non è
			 * nulla (web-page nulla = fine input)
			 */
		} while (page == null);

		return page;
	}

	/**
	 * Converte un record Warc in un oggetto WebPage
	 * 
	 * @param record
	 * @return
	 */
	private WebPage warcRecordToWebPage(WarcRecord record) {
		WebPage webPage = null;

		String trecID = (record.getHeader("WARC-TREC-ID").value.split("-"))[3];
		String pageHtml = streamToString(record.getPayloadContent());
		webPage = new WebPage(trecID, pageHtml);

		return webPage;
	}

	/**
	 * Legge uno stream di tipo InputStream convertendone in contenuto in una
	 * stringa
	 * 
	 * @param is
	 * @return
	 */
	private String streamToString(InputStream is) {
		String str;
		Scanner scan = new Scanner(is);
		scan.useDelimiter("\\A");

		str = scan.hasNext() ? scan.next() : "";
		scan.close();

		return str;
	}

}
