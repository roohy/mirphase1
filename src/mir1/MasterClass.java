package mir1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import mirsearch.SearchClass;

public class MasterClass {
	IndexMaker index ;
	SearchClass searcher;
	
	public IndexMaker getIndexMaker(){
		return this.index;
	}
	
	public IndexMaker makeIndex(String docAddr, boolean stopWord , String stopWrodsDoc
			,boolean biwordSupport, int maxBiwordCount){
		
		ReadFile files = new ReadFile(docAddr);
		List<Doc> docs = files.getDocsBuffers();
		DocMap mapper = new DocMap(docs);
		mapper.mapIt();//mapping the term doc list
		
		//at this line we get a list of docs whose tokens fields are filled with their tokens
		docs = mapper.getMappedDocs();
		
		//making index;

		IndexMaker index = new IndexMaker();
		List<TermDoc> tdList = null;
		List<TermDoc> bdList = null;
		List<String> bdTerms = null;
		if( biwordSupport){
			tdList = mapper.getTermDocMap();
			mapper.makeBiwordList(maxBiwordCount);
			bdList = mapper.bwList;
			bdTerms = mapper.bwTerms;
			index.addTDList(tdList);
			index.compileList();
			index.supportBiwords(bdTerms, bdList);
		}else{
			tdList = mapper.getTermDocMap();
			index.addTDList(tdList);
			index.compileList();
		}
		if(stopWord){
			index.stopWordTermination(stopWrodsDoc);
		}
		//now we print index to test the program
		index.printIndex();
		
		this.index = index;
		this.searcher = new SearchClass(this.index);
		return index;
	}
	public List<Integer> Search(String query, int maxCount , int searchType ){
		return this.searcher.searchIt(query, maxCount, searchType);
	}
	public List<String> getDictionary(){
		return index.getDictionary();
	}
	
	public List<String> generateTokens(File file){
		List<String> result =  new ArrayList<String>();
		try{
			BufferedReader bf = new BufferedReader(new FileReader(file));
			Doc d = new Doc(bf, "HAHA", 1);
			List<Doc> docs = new ArrayList<Doc>();
			docs.add(d);
			DocMap mapper = new DocMap(docs);
			mapper.mapIt();
			docs = mapper.getMappedDocs();
			List<Token> tokens = docs.get(0).tokens;
			for(Token t:tokens){
				result.add(t.getContent());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	public void saveIndex(String addr){
		IndexSaver.saveIndex(index, addr);
	}
	
	public void loadIndex(String addr){
		this.index = IndexSaver.loadIndex(addr);
	}
	public void PrintPostingList(){
		this.index.printIndex();
	}
	public void savePostingList(String addr){
		this.index.saveIndex(addr);
	}
	/*
	public List<String> generateDictionary(String addr){
		
	}*/
	
}
