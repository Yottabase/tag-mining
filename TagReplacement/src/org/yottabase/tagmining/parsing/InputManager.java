package org.yottabase.tagmining.parsing;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.jwat.warc.WarcRecord;
import org.yottabase.tagmining.core.WebPage;

public class InputManager implements InterfaceInputManager {

	private InterfaceParser parser;

	private List<String> inputSources;

	private Iterator<String> iter;

	private InterfaceValidator validator;

	public InputManager(String... paths) {
		this.validator = new WarcRecordValidator();

		this.inputSources = new LinkedList<String>();
		for (String p : paths)
			this.findInputSources(new File(p));

		this.iter = inputSources.iterator();

		this.loadNextInputSource();
	}

	/**
	 * Ricerca tutte le potenziali sorgenti di input Una sorgente di input è un
	 * file con estensione .warc Se il file è una cartella, vengono ricercate
	 * tutte le sorgente di input ad essa radicate, altrimenti il file stesso è
	 * considerata una sorgente di input
	 * 
	 * @param file
	 */
	private void findInputSources(File file) {
		String path = file.getPath();

		if (file.isFile()) {
			if (path.endsWith(".warc"))
				inputSources.add(path);
		} else {
			File[] children = file.listFiles();

			for (File f : children)
				findInputSources(f);
		}
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
			 * Se esistente prendi un record non nullo Un record è nullo quando
			 * l'input corrente è terminato Se ci sono altri input ma sono vuoti
			 * i record successivi possono continuare ad essere nulli
			 */
			while ((record = parser.getNextRecord()) == null && this.hasNextInputSource())
				loadNextInputSource();

			/*
			 * Se il miglior record pescato tra tutti i file di input rimanenti
			 * è comunque nullo allora l'input è terminato: si chiude il parser
			 */
			if (record == null) {
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
	 * Restituisce true se all'interno dei path configurati vi sono ulteriori
	 * sorgenti di input a disposizione, false altrimenti.
	 * 
	 * @return
	 */
	private boolean hasNextInputSource() {
		return iter.hasNext();
	}

	/**
	 * Restituisce la prossima sorgente di input disponibile.
	 * 
	 * @return
	 */
	private String getNextInputSource() {
		return iter.next();
	}

	/**
	 * Carica la prossima sorgente di input
	 */
	private void loadNextInputSource() {
		String inputSource = this.getNextInputSource();
		loadInputSource(inputSource);

		System.out.println("Analisi del file: " + inputSource);
	}

	/**
	 * Effettua il caricamento della corrente sorgente di input
	 */
	private void loadInputSource(String inputSource) {
		if (this.parser != null)
			this.parser.close();

		this.parser = new WarcParser(inputSource);
	}

	/**
	 * Converte un record Warc in un oggetto WebPage
	 * 
	 * @param record
	 * @return
	 */
	private WebPage warcRecordToWebPage(WarcRecord record) {
		WebPage webPage = null;

		String trecID = record.getHeader("WARC-TREC-ID").value;
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
