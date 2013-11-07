package mirsearch;

import java.util.ArrayList;
import java.util.List;

import mir1.IndexMaker;

public class SearchClass {
	IndexMaker index;
	BoolSearch bSearcher;
	TotalCountSearch tCountSearcher;
	WordCountSearch wCountSearcher;
	
	public SearchClass(IndexMaker input) {
		// TODO Auto-generated constructor stub
		this.index = input;
		this.bSearcher = new BoolSearch(index);
		this.tCountSearcher = new TotalCountSearch(index);
		this.wCountSearcher = new WordCountSearch(index);
	}
	
	public List<Integer> searchIt(String query, int maxLen, int searchType){
		List<Integer> result = new ArrayList<Integer>();//dummy function may be implemented after implementing search classes
		
		switch(searchType){ //TODO implement this after implementig query tokenizin subsystem
		case 0:
			result = null;
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
