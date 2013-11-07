package mir1;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class DocMap {
	
	List<Doc> docs;
	List<TermDoc> tdList;
	
	
	DocMap(List<Doc> docs){
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
		Tokenizer tokenizer = new Tokenizer(null, rules);
		
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
	
}
