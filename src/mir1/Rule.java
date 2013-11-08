package mir1;

import java.util.regex.Pattern;

public class Rule{
	final String name;
	final Pattern pattern;
	public Rule(String name , String regex){
		this.name = name;
		pattern = Pattern.compile(regex);
	}
}