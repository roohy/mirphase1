package mir1;

import java.io.BufferedReader;
import java.util.List;

//this class implements docs. with their name and tokens and their buffers
public class Doc {
	BufferedReader buffer;
	String name;
	List<Token> tokens;
	int docID;
	Doc(BufferedReader bf , String name, int ID){
		this.buffer = bf;
		this.name = name;
		tokens = null;
		docID = ID;
	}
	
}
