package mir1;

import Evaluation.Evalulator;

public class Tester2 {
public static void main(String[] args) {
	

	System.out.println("hello");
	Evalulator evaluator = null ;
	try {
		evaluator = new Evalulator("Data/Queries", "Data/Docs", "Data/Relevancy Judgments");
		evaluator.eval() ;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}