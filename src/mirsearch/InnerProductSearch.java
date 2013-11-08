package mirsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mir1.IndexMaker;

public class InnerProductSearch {
	IndexMaker index;
	public InnerProductSearch(IndexMaker input){
		index = input;
	}
	
	public List<Integer> searchIt(Map<String, Integer> queryVector , int maxCout){
		//a map which contains docID - score mapping to support term based mapping
		Map< Integer, Double> docPoints = new HashMap< Integer,Double>();
		
		//we get each term from query vector and find its inverted index
		for(Entry<String, Integer> term: queryVector.entrySet()){
			//here we get posting list as inverted index
			Map<Integer,Integer> temporalMap = index.getSinglePostingList(term.getKey());
			if (temporalMap == null || temporalMap.size()== 0)
				continue;
			//here we check each doc in the posting list added
			for(Entry<Integer,Integer> singleDoc: temporalMap.entrySet()){
				Double dPoint = docPoints.get(singleDoc.getKey());
				if(dPoint == null){
					dPoint = 0.0;
				}
				dPoint += ( Math.log(1.00 + singleDoc.getValue())
						* (Math.log(1.00 + term.getValue().doubleValue())
								*Math.log(index.docNumber()/temporalMap.size()) )  );
				docPoints.put(singleDoc.getKey(), dPoint);
			}
		}
		return sortResults(docPoints, maxCout);
	}
	
	
	
	public static List<Integer> sortResults(Map<Integer,Double> input, int count){
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
	
	public static int maxResult(Map<Integer,Double> input){
		Entry<Integer, Double> maxEntry = null;
		for( Entry<Integer,Double> temporal: input.entrySet()){
			if(maxEntry == null || temporal.getValue() > maxEntry.getValue())
				maxEntry = temporal;
		}
		if( maxEntry == null)
			return -1;
		return maxEntry.getKey().intValue();
	}
	
}
