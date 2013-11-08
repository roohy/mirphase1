package mir1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StopWord {
	
	public BufferedReader getBuffer(String addr){
		BufferedReader bf = null;
		try{
			bf = new BufferedReader(new FileReader(addr));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bf;
	}
	public List<String> getList(String addr){
		List<Rule> rules = new ArrayList<Rule>();
		
		//Adding rules to this list makes applys them in tokenization proccess
		rules.add(new Rule("WORD","[A-Za-z]+"));
		
		
		//here we instantiate a tokenizer with passing null pointer as its doc
		Tokenizer tokenizer = new Tokenizer("", rules);
		
		tokenizer.setBuffer(this.getBuffer(addr));
		tokenizer.Tokenize();
		List<Token> stopTokens = tokenizer.getTokens();
		List<String> stopWords = new ArrayList<String>();
		for ( Token t: stopTokens){
			stopWords.add(t.getContent());
		}
		return stopWords;
	}
	
}
