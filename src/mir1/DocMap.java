package mir1;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DocMap {
	
	List<Doc> docs;
	List<TermDoc> tdList;
	List<TermDoc> bwList;
	
	public DocMap(List<Doc> docs){
		this.docs = docs;
		
	}
	
	public void mapIt(){
		System.out.println("MapIt Function starting to mapp docs");
		List<Rule> rules = new ArrayList<Rule>();
		
		System.out.println("Making Rules...");
		//Adding rules to this list makes applys them in tokenization proccess
		rules.add(new Rule("WORD","[A-Za-z]+"));
		

		System.out.println("Making the Tokenizer");
		//here we instantiate a tokenizer with passing null pointer as its doc
		Tokenizer tokenizer = new Tokenizer("", rules);
		
		for( Doc doc: docs){
			System.out.println("Document "+doc.name+" is starting to be tokenized");
			tokenizer.setBuffer(doc.buffer);
			tokenizer.Tokenize();
			
			doc.tokens = tokenizer.getTokens();
		}
	}
	
	public List<Doc> getMappedDocs(){ 
		return this.docs;
		
	}
	
	//here we have a new part of this class which converts the mapped docs to an array of (term,docName)
	
	public void makeTermDocMap(){
		this.tdList = new ArrayList<TermDoc>();
		for( Doc doc: this.docs){
			for ( Token token: doc.tokens){
				tdList.add(new TermDoc(token.content,doc.docID));
			}
		}
	}
	
	public List<TermDoc> getTermDocMap(){
		if(this.tdList == null){
			this.makeTermDocMap();
		}
		return tdList;
	}
	
	public void makeBiwordList(int count){
		this.bwList = new ArrayList<TermDoc>();
		for( Doc doc: this.docs){
			for( int i = 1 ; i<doc.tokens.size(); i++){
				bwList.add(new TermDoc(doc.tokens.get(i)+" "+doc.tokens.get(i-1), doc.docID));
			}
		}
		Map<String, Integer> occurences = new HashMap<String, Integer>();
		for(TermDoc td: this.bwList){
			Integer occ = occurences.get(td.term);
			if(occ == null)
				occ = 0;
			occ++;
			occurences.put(td.term, occ);
		}
		List<String> maxBiWords = DocMap.sortResults(occurences, count);
		for()
	}
	
	
	public static List<String> sortResults(Map<String,Integer> input, int count){
		List<String> results = new ArrayList<String>();
		for ( int i = 0 ; i < count ; i++){
			String maxTerm = maxResult(input);
			if( maxTerm == ""){
				break;
			}
			results.add(maxTerm);
			input.remove(maxTerm);
		}
		
		return results;
	}
	
	public static String maxResult(Map<String,Integer> input){
		Entry<String, Integer> maxEntry = null;
		for( Entry<String,Integer> temporal: input.entrySet()){
			if(maxEntry == null || temporal.getValue() > maxEntry.getValue())
				maxEntry = temporal;
		}
		if( maxEntry == null)
			return "";
		return maxEntry.getKey();
	}
	
}
