package mir1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IndexMaker {
	List<TermDoc> inputTD;
	Map<String, IndexItem> index;
	
	IndexMaker(){
		inputTD = null;
		index = new HashMap<String,IndexItem>();
	}
	
	public void addTDList(List<TermDoc> list){
		inputTD = list;
	}
	
	public void compileList(){
		if (inputTD == null){
			System.out.println("Error:input list for compiling into index is empty!!!");
			return;
		}
		for (TermDoc td: this.inputTD){
			addToIndex(td.term, td.doc);
		}
	}
	
	
	public void addToIndex(String term , String doc){
		IndexItem temporal = this.index.get(term);
		if( temporal == null){
			temporal = new IndexItem(term);
		}
		temporal.incrementDoc(doc);
		this.index.put(term, temporal);
	}
	
	public void printIndex(){
		Iterator<Map.Entry<String,IndexItem>> iter = index.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, IndexItem> tempEntry = (Map.Entry<String,IndexItem>)iter.next();
			tempEntry.getValue().printItem();
		}
	}
}
