package mirsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mir1.Indexer;

public class WordCountSearch {
	Indexer index;
	public WordCountSearch(Indexer index) {
		this.index = index;
	}
	
	
	public List<Integer> searchIt(List<String> terms, int maxCount){
		List<Integer> result;
		Map<Integer,Integer> queryDoc = new HashMap<Integer,Integer>();
		for (String term : terms){
			List<Integer> temporalList = index.getPostingListNoFreq(term);
			for(Integer docID: temporalList){
				Integer totalCount = queryDoc.get(docID);
				if( totalCount == null)
					totalCount = 0;
				totalCount++;
				queryDoc.put(docID, totalCount);
			}
		}
		//now we give the hash map to sort results and then get a list of n best answers
		result = sortResults(queryDoc, maxCount);
		
		return result;
	}
	//this function gets the hash code and returns "count"th maximum values
	public static List<Integer> sortResults(Map<Integer,Integer> input, int count){
		List<Integer> results = new ArrayList<Integer>();
		for ( int i = 0 ; i < count ; i++){
			int maxDocID = maxResult(input);
			if( maxDocID == -1){
				break;
			}
			results.add(maxDocID);
			input.remove(maxDocID);
		}
		
		return results;
	}
	
	public static int maxResult(Map<Integer,Integer> input){
		Entry<Integer, Integer> maxEntry = null;
		for( Entry<Integer,Integer> temporal: input.entrySet()){
			if(maxEntry == null || temporal.getValue() > maxEntry.getValue())
				maxEntry = temporal;
		}
		if( maxEntry == null)
			return -1;
		return maxEntry.getKey().intValue();
	}
}
