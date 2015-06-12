package org.yottabase.tagmining.output;

import java.io.FileWriter;
import java.io.IOException;
import org.yottabase.tagmining.core.Phrase;
import org.yottabase.tagmining.core.TaggedWord;

public class OutputManager {
	
	private InterfaceFormatter formatter = new TSVFormatter();
	private FileWriter out1; 	// stringa da rimpiazzare:		trec-id		string_da_rimpiazzare		tag
	private FileWriter out2;	// frase estratta senza tag:	trec-id		frase_estratta_senza_tag
	private FileWriter out3;	// frase estratta con tag:		trec-id		frase_estratta_con_tag	
	
	public OutputManager(String path, String fileId) throws IOException {
		
		out1 = new FileWriter(path + "/" + fileId + "_out1.txt");
		out2 = new FileWriter(path + "/" + fileId + "_out2.txt");
		out3 = new FileWriter(path + "/" + fileId + "_out3.txt");
		
		out1.write(formatter.format("trec-id", "stringa_da_rimpizziare", "tag"));
		out2.write(formatter.format("trec-id", "frase_estratta_senza_tag"));
		out3.write(formatter.format("trec-id", "frase_estratta_con_tag"));
	}
	
	public void writePhrase(Phrase phrase) throws IOException{
		for(TaggedWord tw : phrase.getTaggedWords()){
			out1.write(formatter.format(phrase.getTrecID(), tw.getWord(), tw.getTag()));
		}
		
		out2.write(formatter.format(phrase.getTrecID(), phrase.getPhrase()));
		
		out3.write(formatter.format(phrase.getTrecID(), phrase.getTaggedPhrase()));
	}
	
	public void close() throws IOException{
		out1.close();
		out2.close();
		out3.close();
	}

}
