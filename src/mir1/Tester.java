package mir1;

import java.util.List;

public class Tester {
	public static void main(String args[]){
		ReadFile files = new ReadFile("/home/roohy/workspace/data");
		
		
		List<Doc> docs = files.getDocsBuffers();
		DocMap mapper = new DocMap(docs);
		mapper.mapIt();//mapping the term doc list
		
		//at this line we get a list of docs whose tokens fields are filled with their tokens
		docs = mapper.getMappedDocs();
		
		/* but we dont need that for next step. we need list of (Term ,DocName)
		 * which we will make at this step below
		 */
		List<TermDoc> tdList = mapper.getTermDocMap();
		
		IndexMaker index = new IndexMaker();
		//first we add the list as a new list to indexer we can do this multiple time 
		index.addTDList(tdList);
		//then we compile the list into the index, we have to do this every time we add a new td list
		index.compileList();
		//now we print index to test the program
		index.printIndex();
	}
}
