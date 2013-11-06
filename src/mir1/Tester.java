package mir1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tester {
	public static void main(String args[]){
		ReadFile files = new ReadFile("/home/roohy/workspace/data");
		BufferedReader thebuffer = files.getNextBuffer();
		String line ;
		try{

			while( (line = thebuffer.readLine()) != null){
				StringTokenizer mytokenizer = new StringTokenizer(line);
				while(mytokenizer.hasMoreElements()){
					System.out.println(mytokenizer.nextToken());
				}
			}
		}catch(IOException e ){
			e.printStackTrace();
		}
	}
}
