package mir1;

public class TermDoc {
	String term;
	//String doc;
	int docID;
	
	public TermDoc(String t, int d){
		term = t;
		docID = d;
	}
	public boolean equals(TermDoc td){
		if (td == null)
			return false;
		return (td.term==this.term && td.docID==this.docID);
	}
}
