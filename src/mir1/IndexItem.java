package mir1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class IndexItem {
	String name;
	int DocFreq;
	Map<String,Integer> docs;
	public IndexItem(String name) {
		
		this.name = name;
		this.DocFreq = 0;
		this.docs = new HashMap<String, Integer>();
		// TODO Auto-generated constructor stub
	}
	
	public void incrementDoc(String docName){
		Integer temp = docs.get(docName);
		if( temp != null){
			temp += 1;
			docs.put(docName, temp);
		}
		else{
			docs.put(docName, new Integer(1));
		}
		DocFreq++;
	}
	
	public void printItem(){
		Iterator<Entry<String,Integer>> iterator = this.docs.entrySet().iterator();
		System.out.print("term: "+this.name+"["+this.DocFreq+"]:");
		while(iterator.hasNext()){
			Entry<String, Integer> temporalEntry = (Entry<String,Integer>)iterator.next();
			System.out.print(""+temporalEntry.getKey()+":"+temporalEntry.getValue()+" ");
		}
		System.out.println("");
	}
	public void saveItem(BufferedWriter buffer) throws IOException {
		Iterator<Entry<String,Integer>> iterator = this.docs.entrySet().iterator();
		buffer.write("term: "+this.name+"["+this.DocFreq+"]:");
		while(iterator.hasNext()){
			Entry<String, Integer> temporalEntry = (Entry<String,Integer>)iterator.next();
			buffer.write(""+temporalEntry.getKey()+":"+temporalEntry.getValue()+" ");
		}
		buffer.newLine();
	}
}
