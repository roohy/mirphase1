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
			List<Integer> tempList = this.index.getPostingListNoFreq(term);
			if(tempList == null)
				continue;
			list.add(tempList);
		}
		if( list.size() == 0){
			System.out.println("No results were found in bool search");
			return result;
		}
		if( list.size() == 1 ){
			System.out.println("size of this is "+list.get(0).size());
			return list.get(0);
			
		}
		System.out.println("size of lists is "+list.get(0).size()+" and "+list.get(1).size());
		result = SearchClass.intersection(list.get(0), list.get(1));
		for ( int i = 2; i < list.size(); i++){
			result = SearchClass.intersection(result, list.get(i));
		}
		return result;
	}
	
	//the two next functions are for test purposes :D dont read them :D
	public static void printLists(List<List<Integer>> list){
		System.out.println("printing the results list boolean search");
		for(List<Integer> li : list){
			for(Integer temp: li){
				System.out.print(""+temp+",");
			}
			System.out.println("");
		}
	}
	public static void printList(List<Integer> list){
		System.out.println("printing list:");
		for(Integer temp : list){
			System.out.print(""+temp+", ");
		}
		System.out.println("");
	}
}
