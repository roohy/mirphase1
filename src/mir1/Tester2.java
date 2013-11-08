package mir1;

import Evaluation.Evalulator;
import Graphics.MainWindow;

public class Tester2 {
public static void main(String[] args) {
	
	new MainWindow() ;
	System.out.println("hello");
	Evalulator evaluator = null ;
	try {
//		evaluator = new Evalulator("Data/Queries", "Data/Docs", "Data/Relevancy/relevance");
	//	evaluator.eval() ;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}