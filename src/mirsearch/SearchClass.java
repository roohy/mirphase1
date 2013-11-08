package mirsearch;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mir1.IndexMaker;
import mir1.Token;
import mir1.Tokenizer;
import mir1.Rule;

public class SearchClass {
	IndexMaker index;
	BoolSearch bSearcher;
	TotalCountSearch tCountSearcher;
	WordCountSearch wCountSearcher;
	InnerProductSearch innerPSearch;
	CosineSearch coSearch;
	List<Token> queryTokens;
	
	
	public SearchClass(IndexMaker input) {
		// TODO Auto-generated constructor stub
		this.index = input;
		this.bSearcher = new BoolSearch(index);
		this.tCountSearcher = new TotalCountSearch(index);
		this.wCountSearcher = new WordCountSearch(index);
		this.innerPSearch = new InnerProductSearch(index);
		this.coSearch = new CosineSearch(index);
	}
	
	public void tokenizeQuery(String query){
		List<Rule> rules = new ArrayList<Rule>();
//		queryTokens = new ArrayList<Token>();
		//System.out.println("Making Rules...");
		//Adding rules to this list makes applys them in tokenization proccess
		rules.add(new Rule("WORD","[A-Za-z]+"));
		
		Tokenizer tokenizer = new Tokenizer(query,rules);
		tokenizer.Tokenize();//tokenizing the query
		this.queryTokens = tokenizer.getTokens();
		
	}
	
	//tokens list may contain duplicates elements but in first three search options we dont 
	// want that, so here we eradicate the duplicate elements from exsistance!!! :D
	public void removeDuplicates(){
		Set<Token> tokenSet= new LinkedHashSet<Token>(this.queryTokens);
		this.queryTokens.clear();
		this.queryTokens.addAll(tokenSet);
	}
	
	//returns list of terms as string type 
	//we get the items from query tokens so if there duplication there there is here too;)
	public List<String> queryTerms(){
		List<String> terms = new ArrayList<String>();
		for(Token t: this.queryTokens){
			terms.add(t.getContent());
		}
		return terms;
	}
	
	public Map<String, Integer> queryVector(){
		Map<String,Integer> result = new HashMap<String, Integer>();
		for(Token t: this.queryTokens){
			Integer temp = result.get(t.getContent());
			if(temp == null)
				temp = 0;
			temp++;
			result.put(t.getContent(), temp);
			
		}
		return result;
	}
	
	//the main function for search is this
	public List<Integer> searchIt(String query, int maxLen, int searchType){
		List<Integer> result;//dummy function may be implemented after implementing search classes
		tokenizeQuery(query);
		for(String st: this.queryTerms()){
			System.out.println("search term: "+st);
		}
		switch(searchType){ //TODO implement this after implementig query tokenizin subsystem
		case 0:
			removeDuplicates();
			result = bSearcher.searchIt(this.queryTerms(), maxLen);
			break;
		case 1:
			removeDuplicates();
			result = wCountSearcher.searchIt(this.queryTerms(),maxLen);
			break;
		case 2:
			removeDuplicates();
			result = tCountSearcher.searchIt(this.queryTerms(), maxLen);
			break;
		case 3:
			result = innerPSearch.searchIt(this.queryVector(), maxLen);
			break;
		case 4:
			result = coSearch.searchIt(this.queryVector(), maxLen);
			break;
		default:
			result = null;
			break;
		}
		
		return result;
	}
	
	
	  public static <T> List<T> intersection(List<T> list1, List<T> list2) {
	        List<T> list = new ArrayList<T>();

	        for (T t : list1) {
	            if(list2.contains(t)) {
	                list.add(t);
	            }
	        }

	        return list;
	    }
}
