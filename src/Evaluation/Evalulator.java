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
import mir1.Indexer;
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
			String answerAddress) throws NumberFormatException, IOException {
		super();
		this.queryPrefix = queryPrefix;
		this.docsPrefix = docsPrefix;
		this.answerAddress = answerAddress;
		queryAnswers = new HashMap<Integer, List<Integer>>() ;
		buildMap() ; 
		getQuerys() ;
		
		for ( Query a : querys){
			System.out.println("num: " + a.queryNum + " query: " + a.query);
		}
		
	}
	
	private void buildMap() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("Data/Relevancy/relevance")));
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

	public void eval () throws FileNotFoundException{
		Indexer index = getIndex() ; 
		searcher = new SearchClass(index);
		for( int i = 0 ; i < 3 ; i++){
			for (Query q : querys) {
				List<Integer> searchResults = searcher.searchIt(q.query, 20, i);
				List<Integer> correctResults = queryAnswers.get(q.queryNum); 
				System.out.println("search results: " + test(searchResults));
				System.out.println("correct results: " + test(correctResults));
			}
		}
		
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

	private Indexer getIndex(){
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
		
		Indexer index = new Indexer();
		//first we add the list as a new list to indexer we can do this multiple time 
		index.addTDList(tdList);
		//then we compile the list into the index, we have to do this every time we add a new td list
		index.compileList();
		return index ;
	}
	
}
