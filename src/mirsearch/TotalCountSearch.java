package mirsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mir1.IndexMaker;

public class TotalCountSearch {
	IndexMaker index;
	
	public TotalCountSearch(IndexMaker index) {
		this.index = index;
	}
	
	public List<Integer> searchIt(List<String> terms, int maxCount){
		List<Integer>  result ;
		List<Map<Integer,Integer>> pairs= index.getPostingListComplete(terms);
		Map<Integer,Integer> unionMap = new HashMap<Integer, Integer>();
		for(Map<Integer,Integer> temporal: pairs){
			for(Entry<Integer, Integer> tentry: temporal.entrySet()){
				Integer docFreq = unionMap.get(tentry.getKey());
				if( docFreq == null)
					docFreq = 0;
				docFreq += tentry.getValue();
				unionMap.put(tentry.getKey(), docFreq);
			}
		}
		result = WordCountSearch.sortResults(unionMap, maxCount);
		return result;
	}
	
}
