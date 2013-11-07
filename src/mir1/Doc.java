package mir1;

import java.io.BufferedReader;
import java.util.List;

//this class implements docs. with their name and tokens and their buffers
public class Doc {
	BufferedReader buffer;
	String name;
	List<Token> tokens;
	
	Doc(BufferedReader bf , String name){
		this.buffer = bf;
		this.name = name;
		tokens = null;
	}
}
