package mir1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndexItem implements Serializable , Comparable<IndexItem>{
	String name;
	int DocFreq;
	Map<Integer,Integer> docs;
	public IndexItem(String name) {
		
		this.name = name;
		this.DocFreq = 0;
		this.docs = new HashMap<Integer, Integer>();
		// TODO Auto-generated constructor stub
	}
	
	public void incrementDoc(int docName){
		Integer temp = docs.get(docName);
		if( temp != null){
			temp += 1;
			docs.put(docName, temp);
		}
		else{
			docs.put(docName, new Integer(1));
			DocFreq++;
		}
	}
	
	public String getname(){
		return this.name ; 
	}
	public int getDocFreq(){
		return this.DocFreq ;
	}
	public String toString(){
		String result = this.name + ": " ;
		result += this.DocFreq + " - " ; 
		
		Iterator<Entry<Integer,Integer>> iterator = this.docs.entrySet().iterator();
		//System.out.print("term: "+this.name+"["+this.DocFreq+"]:");
		while(iterator.hasNext()){
			Entry<Integer, Integer> temporalEntry = (Entry<Integer,Integer>)iterator.next();
			result += temporalEntry.getKey() + " [" + temporalEntry.getValue() + "] , " ; 
//			System.out.print(""+temporalEntry.getKey()+":"+temporalEntry.getValue()+" ");
		}
		return result  ;
	}
	
	public void printItem(){
		Iterator<Entry<Integer,Integer>> iterator = this.docs.entrySet().iterator();
		System.out.print("term: "+this.name+"["+this.DocFreq+"]:");
		while(iterator.hasNext()){
			Entry<Integer, Integer> temporalEntry = (Entry<Integer,Integer>)iterator.next();
			System.out.print(""+temporalEntry.getKey()+":"+temporalEntry.getValue()+" ");
		}
		System.out.println("");
	}
	public void saveItem(BufferedWriter buffer) throws IOException {
		Iterator<Entry<Integer,Integer>> iterator = this.docs.entrySet().iterator();
		buffer.write("term: "+this.name+"["+this.DocFreq+"]:");
		while(iterator.hasNext()){
			Entry<Integer, Integer> temporalEntry = (Entry<Integer,Integer>)iterator.next();
			buffer.write(""+temporalEntry.getKey()+":"+temporalEntry.getValue()+" ");
		}
		buffer.newLine();
	}
	
	
	//this function return a string which shows the posting list for this particular term
	//use this for print and UI purposes
	public String getPostingListString(){
		String result = new String("");
		Iterator<Entry<Integer,Integer>> iterator = this.docs.entrySet().iterator();
		result = result +this.name+": "+this.DocFreq+" - ";
		while(iterator.hasNext()){
			Entry<Integer, Integer> temporalEntry = (Entry<Integer,Integer>)iterator.next();
			result = result+temporalEntry.getKey()+"["+temporalEntry.getValue()+"], ";
		}
		
		return result;
	}
	
	/* this function returns the posting list for this particual index Item and term
	 * as a list of integers, which contains the docIDs from posting list and contains nothing abut frequency of terms
	 */
	public List<Integer> getPostingListNoFreq(){
		
		List<Integer> result = new ArrayList<Integer>();
		result.addAll(docs.keySet());
		return result;
	}
	
	public Map<Integer,Integer> getPostingListComplete(){
		return this.docs;
	}

	@Override
	public int compareTo(IndexItem o) {
		if ( this.DocFreq > o.DocFreq)
			return -1 ; 
		if ( this.DocFreq <  o.DocFreq)
			return 1 ; 
		return 0;
	}
}

	
