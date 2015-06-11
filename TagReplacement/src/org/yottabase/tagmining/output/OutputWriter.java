package org.yottabase.tagmining.output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class OutputWriter {
	
	private PrintWriter out1; 	// stringa da rimpiazzare:		trec-id		string_da_rimpiazzare		tag
	private PrintWriter out2;	// frase estratta senza tag:	trec-id		frase_estratta_senza_tag
	private PrintWriter out3;	// frase estratta con tag:		trec-id		frase_estratta_con_tag	
	
	private static String SEPARATOR = "\t";
	
	public OutputWriter(String path, String fileId) throws FileNotFoundException {
		
		out1 = new PrintWriter(path + "/" + fileId + "_out1.txt");
		out2 = new PrintWriter(path + "/" + fileId + "_out2.txt");
		out3 = new PrintWriter(path + "/" + fileId + "_out3.txt");
		
		out1.write("trec-id" + SEPARATOR + "stringa_da_rimpizziare" + SEPARATOR + "tag" );
		
	}
	
	public void writePhrase(Phrase phrase){
		this.writePhraseOutput1(phrase);
		this.writePhraseOutput2(phrase);
		this.writePhraseOutput3(phrase);
	}
	
	
	private void writePhraseOutput1(Phrase phrase){
		for(TaggedWord tw : phrase.getTaggedWords()){
			out1.write(phrase.getTrecID() + SEPARATOR + tw.getWord() + SEPARATOR + tw.getTag());
		}
	}
	
	private void writePhraseOutput2(Phrase phrase){
		out2.write(phrase.getTrecID() + phrase.getPhrase());
	}

	private void writePhraseOutput3(Phrase phrase){
		out3.write(phrase.getTrecID() + phrase.getTaggedPhrase());
	}

}
