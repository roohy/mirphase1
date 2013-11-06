package mir1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tokenizer {
	private BufferedReader bufferReader;
	String lastLine;
	StringTokenizer tokenizer;
	public Tokenizer() {
		// TODO Auto-generated constructor stub
		bufferReader = null;
		lastLine = null;
	}
	
	public void setBuffer(BufferedReader theBuffer){
		this.bufferReader = theBuffer;
	}
	
	public boolean hasNext(){
		
		//checking if current tokenizer has more elements
		if(tokenizer.hasMoreElements()){
			return true;
		}
		if(lastLine == null){
			try{
				lastLine = bufferReader.readLine();
				if(lastLine == null){
					return false;
				}
				else{
					
				}
			}catch(IOException e){
				e.printStackTrace();
				return false;
			}
		
		
		}
		
		
		

		return false;
	}
}
