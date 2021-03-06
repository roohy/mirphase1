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
		this.index.updateDocLength();
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
		rules.add(new Rule("WORD","[A-Za-z\\.]+"));
		rules.add(new Rule("URL","(https?)://[-.a-z0-9]+\\.[a-z]{2,3}(/[a-z0-9/-]*)?"));
		rules.add(new Rule("Mail","[-.a-z0-9]+@[-.a-z0-9]+\\.[a-z]{2,3}"));
		
		Tokenizer tokenizer = new Tokenizer(query,rules);
		tokenizer.Tokenize();//tokenizing the query
		this.queryTokens = tokenizer.getTokens();
		if(index.doesSupportBiwords()){
			this.checkBiwords();
		}
		
	}
	
	
	public void checkBiwords(){
		List<Token> tempList = new ArrayList<Token>();
		List<Integer> toDeleteList = new ArrayList<Integer>();
		for( int i = 1 ;i< this.queryTokens.size(); i++){
			if(this.index.checkBiword(this.queryTokens.get(i).getContent()+" "
				+this.queryTokens.get(i).getContent() )){
				tempList.add(new Token("haha"
						,this.queryTokens.get(i).getContent()+" "
								+this.queryTokens.get(i).getContent()));
				toDeleteList.add(i);
				i++;
			}
		}
		for(int i = toDeleteList.size()-1 ; i> -1 ; i--){
			this.queryTokens.remove(toDeleteList.get(i));
			this.queryTokens.remove(toDeleteList.get(i)-1);
		}
		for( Token t:tempList){
			queryTokens.add(t);
		}
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
			System.out.println("updating query vector "+t.getContent()+" to "+temp);
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
			result = bSearcher.searchIt(this.queryTerms(), Integer.MAX_VALUE);
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
			System.out.println("Search Query Type is So Wrong I will Throw an exception");
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
