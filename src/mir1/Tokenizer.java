package mir1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	
	
	List<Rule> rules;
	private BufferedReader bufferReader;
	String lastLine;
	Token nextToken;
	List<Token> tokensList;
	int readTokens;
	//StringTokenizer tokenizer;
	
	
	public Tokenizer() {
		// TODO Auto-generated constructor stub
		bufferReader = null;
		lastLine = null;
		List<Rule> rules = new ArrayList<Rule>();
		
	}
	Tokenizer(BufferedReader bf , List<Rule> rules){
		this.bufferReader  = bf;
		this.rules = rules;
		this.nextToken = null;
		this.tokensList = new ArrayList<Token>();
	}
	public void addRule(Rule rule){
		this.rules.add(rule);
	}
	
	
	public void Tokenize(){
		int pos = 0; //?
		int end;
		String line;
		try{

			while((line = this.bufferReader.readLine()) != null){
				pos = 0;
				end = line.length();
				Matcher matcher = Pattern.compile("dummy").matcher(line);
			    matcher.useTransparentBounds(true).useAnchoringBounds(false);
				while(pos<end){
					matcher.region(pos, end);
					for (Rule rule: rules){
						if(matcher.usePattern(rule.pattern).lookingAt()){
							this.tokensList.add(new Token(rule.name,matcher.start(),matcher.end()));
							pos = matcher.end();
							break;
						}
					}
					pos++;
				}
			    
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		//Matcher m = Pattern.compile("dummy");
		
		
	}
	
	public void setBuffer(BufferedReader theBuffer){
		this.bufferReader = theBuffer;
	}
	public List<Token> getTokens(){
		return this.tokensList;
	}
	/*public boolean hasNext(){
		
	}*/
	
	
	
}
