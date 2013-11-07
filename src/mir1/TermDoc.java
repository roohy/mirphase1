package mir1;

public class TermDoc {
	String term;
	String doc;
	TermDoc(String t, String d){
		term = t;
		doc = d;
	}
	public boolean equals(TermDoc td){
		if (td == null)
			return false;
		return (td.term==this.term && td.doc==this.doc);
	}
}
