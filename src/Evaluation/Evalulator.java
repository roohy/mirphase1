package Evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mir1.Doc;
import mir1.DocMap;
import mir1.IndexMaker;
import mir1.ReadFile;
import mir1.TermDoc;
import mirsearch.SearchClass;


public class Evalulator {
	private String queryPrefix ; 
	private String docsPrefix ; 
	private String answerAddress ;
	private SearchClass searcher ; 
	Map <Integer , List<Integer>> queryAnswers ; 
	ArrayList<Query> querys ; 
	public Evalulator(String queryPrefix, String docsPrefix,
			String answerAddress)  {
		super();
		this.queryPrefix = queryPrefix;
		this.docsPrefix = docsPrefix;
		this.answerAddress = answerAddress;
		queryAnswers = new HashMap<Integer, List<Integer>>() ;
		try {
			buildMap() ;
			getQuerys() ;
			eval() ; 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	
	private void buildMap() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File(answerAddress)));
		String line ; 
		while((line= br.readLine())!=null){
			if ( line.equals(""))
				continue ; 
			line = line.replaceAll("[ ]+", " ") ;
			int num = Integer.parseInt(line.substring(0, line.indexOf(" "))) ; 
			line = line.substring(line.indexOf(" ") + 1, line.length()) ;
			String [] docList = line.split(" ") ; 
			List<Integer> docs = new ArrayList<Integer>();
			for ( String d : docList)
				docs.add(Integer.parseInt(d));
			queryAnswers.put(num, docs) ;
				
        }
	}
	float precission  ,recall , average_precission ;  
	public String eval () throws FileNotFoundException{
		String result = "" ; 
		IndexMaker index = getIndex() ; 
		searcher = new SearchClass(index);
		for( int i = 0 ; i < 4 ; i++){
			precission = 0 ; recall = 0 ; average_precission = 0 ;  
			int max_num = ( i==0)? Integer.MAX_VALUE : 30 ; 
			for (Query q : querys) {
				List<Integer> searchResults = searcher.searchIt(q.query, max_num, i);
				List<Integer> correctResults = queryAnswers.get(q.queryNum); 
				calculateCriterions(searchResults , correctResults) ; 	
				System.err.println("i = "  + i +  "  search results: " + test(searchResults));
				System.err.println("i = "  + i +  "  correct results: " + test(correctResults));
				
			}
			result += ("Model " + i + " R= " + recall/querys.size() + " P= " + precission/querys.size() 
					+ ((i==0)? "" : " MAP= " + (average_precission/querys.size())      ) + "\n");
		}
		return result ;
	}
	
	
	private void calculateCriterions(List<Integer> searchResults,
			List<Integer> correctResults) {
		float correct = 0 , a = 0 ;  
		for(float i = 0 ; i < searchResults.size() ; i++){
			if ( correctResults.contains(searchResults.get((int)i))){
				correct++ ; 
				a += correct/(i+1) ; 
			}
		}
		precission += correct/((float)searchResults.size());
		recall += correct/((float)correctResults.size());
		average_precission += (a/ correct); 
		
		
	}

	private String test(List<Integer> a){
		String result = "";
		for ( Integer b : a){
			result += b + " " ; 
		}
		return result ;
	}
	
	private ArrayList<String> getQuerys() throws IOException {
		querys = new ArrayList<Query>() ;
		File directory = new File(queryPrefix) ;
		File [] queryFiles = directory.listFiles() ;
		BufferedReader bf ; 
		for (File file : queryFiles) {
			int num = Integer.parseInt(file.getName().substring(1));
			bf = new BufferedReader(new FileReader(file) );
			String q = "" , line = "" ;
			while((line= bf.readLine())!=null){
				line = line.replaceAll("[\t\r\n ]+", " ");
				if (line.equals("") || line.equals(" "))
					continue ; 
				q += line ; 
			}
			querys.add(new Query(num, q));
			
			
		}
		
		return null ;
	}

	private IndexMaker getIndex(){
		ReadFile files = new ReadFile(docsPrefix);
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
		return index ;
	}
	
}
