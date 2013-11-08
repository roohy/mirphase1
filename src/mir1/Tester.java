package mir1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Evaluation.Evalulator;

import mirsearch.BoolSearch;
import mirsearch.SearchClass;

public class Tester {
	public static void main(String args[]){
		
		System.out.println("hello");
		Evalulator evaluator = null ;
		try {
			evaluator = new Evalulator("Data/Queries", "Data/Docs", "Data/Relevancy Judgments");
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			evaluator.eval() ;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		/*
		ReadFile files = new ReadFile("Data/Docs");
		List<Doc> docs = files.getDocsBuffers();
		DocMap mapper = new DocMap(docs);
		mapper.mapIt();//mapping the term doc list
		
		//at this line we get a list of docs whose tokens fields are filled with their tokens
		docs = mapper.getMappedDocs();
		
		// but we dont need that for next step. we need list of (Term ,DocName)
		 // which we will make at this step below
		 
		List<TermDoc> tdList = mapper.getTermDocMap();
		
		Indexer index = new Indexer();
		//first we add the list as a new list to indexer we can do this multiple time 
		index.addTDList(tdList);
		//then we compile the list into the index, we have to do this every time we add a new td list
		index.compileList();
		//now we print index to test the program
		index.printIndex();
		index.saveIndex("../hi.txt");
		
		
		//here we instantiate a searcher class
	//	SearchClass searcher = new SearchClass(index);
//		List<Integer> searchResults = searcher.searchIt("the only all chance", 10, 1);
//		BoolSearch.printList(searchResults);
		
		
		*/
	}
}
