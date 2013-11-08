package mir1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class IndexMaker implements Serializable{
	List<TermDoc> inputTD;
	Map<String, IndexItem> index;
	int docNumber;
	
	//for supporting biwords
	int biWordSupport;
	List<String> biwordTerms;
	
	
	Set<Integer> docSet;
	Map<Integer,Double> docLength;
	public IndexMaker(){
		inputTD = null;
		index = new HashMap<String,IndexItem>();
		docSet = new TreeSet<Integer>();
		this.biWordSupport = 0;
	}
	
	public boolean doesSupportBiwords(){
		return (biWordSupport == 1);
	}
	public void supportBiwords(List<String> inputTerms,List<TermDoc> newTD){
		this.biWordSupport = 1;
		this.biwordTerms = inputTerms;
		this.addTDList(newTD);
		this.compileList();
	}
	public boolean checkBiword(String term){
		if ( !doesSupportBiwords()){
			return false;
		}
		return this.biwordTerms.contains(term);
	}
	
	public void stopWordTermination(String addr){
		StopWord sWords = new StopWord();
		List<String> stopTokens= sWords.getList(addr);
		for(String st :stopTokens){
			this.index.remove(st);
		}
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
			addToIndex(td.term, td.docID);
			//trying to findout number of docs;
			docSet.add(td.docID);
			
		}
		
	}
	public int docNumber(){
		return docSet.size();
	}
	
	public void addToIndex(String term , int doc){
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
	public void saveIndex(String addr){
		
			try{
				File file = new File(addr);
				if( !file.exists()){
					file.createNewFile();
				}


				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter writebuffer = new BufferedWriter(fw);
				
				
				Iterator<Map.Entry<String,IndexItem>> iter = index.entrySet().iterator();
				while(iter.hasNext()){
					Map.Entry<String, IndexItem> tempEntry = (Map.Entry<String,IndexItem>)iter.next();
					tempEntry.getValue().saveItem(writebuffer);
				}
				
			}catch(IOException e){
				System.out.println("Error check permissions and your address");
				e.printStackTrace();
			}
		
		
	}
	public List<String> getDictionary(){
		List<String> result = new ArrayList<String>();
		result.addAll(this.index.keySet());
		return result;
	}
	public void printDictionary(){
		for( String st: this.index.keySet()){
			System.out.println(st);
		}
	}
	

	//see the comments about get posting list string in index Item class ;)
	public String getPostingListString(String term){
		IndexItem item = this.index.get(term.toLowerCase());
		if(item == null){
			return null;
		}
		else{
			return item.getPostingListString();
		}
	}
	
	
	//see the comments about get posting list no freq in index Item class ;)
	public List<Integer> getPostingListNoFreq(String term){
		IndexItem item = this.index.get(term);
		if( item == null){
			return null;
		}
		else{
			return item.getPostingListNoFreq();
		}
	}
	
	public List<Map<Integer,Integer>> getPostingListComplete(List<String> terms){
		List<Map<Integer,Integer>> results = new ArrayList<Map<Integer,Integer>>();
		for(String term: terms){
			IndexItem temporal = index.get(term);
			if(temporal == null)
				continue;
			results.add(temporal.getPostingListComplete());
		}
		return results;
	}
	
	//this is used for inner prodct porpuses
	public Map<Integer,Integer> getSinglePostingList(String  term){
		
		IndexItem item =  this.index.get(term);
		if ( item == null){
			return null;
		}
		return item.getPostingListComplete();
	}
	public int getDocFrequency(String term){
		IndexItem item= this.index.get(term);
		if( item == null)
			return 0;
		return item.DocFreq;
	}
	
	//this function update doc length map
	//which contains vector length of docs so we can use it in our cosine vector search;)
	public void updateDocLength(){
		this.docLength = null;
		this.docLength = new HashMap<Integer,Double>();
		for(IndexItem item: index.values()){
			Map<Integer, Integer> itemPostings = item.getPostingListComplete(); 
			for(Entry<Integer,Integer> itemMap: itemPostings.entrySet()){
				Double score = docLength.get(itemMap.getKey());
				if(score == null)
					score = 0.0;
				score  += Math.pow(itemMap.getValue(), 2.0);
				docLength.put(itemMap.getKey(), score);
			}
		}
		//now we get square root of every doc score to get doc length
		for(Integer tempKey: docLength.keySet()){
			Double score = docLength.get(tempKey);
			score = Math.sqrt(score);
			docLength.put(tempKey, score);
		}
	}
	public Double getDocLength(Integer key){
		Double result = this.docLength.get(key);
		return result;
	}
}
