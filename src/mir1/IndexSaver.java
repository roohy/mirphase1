package mir1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IndexSaver {
	public static void saveIndex(IndexMaker index, String addr){
		FileOutputStream fstream = null;
		ObjectOutputStream ostream = null;
		try{
			fstream = new FileOutputStream(addr);
			ostream = new ObjectOutputStream(fstream);
			ostream.writeObject(index);
			
			ostream.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		System.out.println("Index saved to "+addr);
	}
	public static IndexMaker loadIndex(String addr){
		FileInputStream fstream = null;
		ObjectInputStream istream = null;
		IndexMaker resultIndex = null;
		try{
			fstream = new FileInputStream(addr);
			istream = new ObjectInputStream(fstream);
			resultIndex = (IndexMaker)istream.readObject();
			istream.close();
		}catch( Exception ex){
			ex.printStackTrace();
		}
		return resultIndex;
	}
}
