package mir1;

import java.util.regex.Pattern;

class Rule{
	final String name;
	final Pattern pattern;
	Rule(String name , String regex){
		this.name = name;
		pattern = Pattern.compile(regex);
	}
}