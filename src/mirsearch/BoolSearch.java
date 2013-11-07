package mirsearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mir1.IndexMaker;

public class BoolSearch {
	IndexMaker index;
	public BoolSearch(IndexMaker input) {
		index = input;
	}
	public List<Integer> searchIt(List<String> terms,int maxLen){
		List<Integer> result = new ArrayList<Integer>();
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for(String term : terms){
			list.add(this.index.getPostingListNoFreq(term));
		}
		if( terms.size() == 1 ){
			return list.get(0);
		}
		
		result = SearchClass.intersection(list.get(0), list.get(1));
		
		for ( int i = 2; i < result.size(); i++){
			result = SearchClass.intersection(result, list.get(i));
		}
		return result;
	}
}
