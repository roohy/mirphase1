package mirsearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mir1.IndexMaker;

public class CosineSearch {
	IndexMaker index;
	public CosineSearch(IndexMaker index){
		this.index = index;
		
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
		Double queryLength = this.getQuerySize(queryVector);
		for( Entry<Integer, Double> score: docPoints.entrySet()){
			Double docScore = score.getValue();
			docScore = docScore/index.getDocLength(score.getKey());
			docScore = docScore/queryLength;
		}
		return InnerProductSearch.sortResults(docPoints, maxCout);
	}
	
	double getQuerySize(Map<String,Integer> query){
		double result= 0;
		for (Entry<String, Integer> entry : query.entrySet()){
			result += Math.pow(entry.getValue(), 2);
			
		}
		result = Math.sqrt(result);
		return result;
		
	}
	
}
