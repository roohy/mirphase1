package mir1;


public class Token{
	final String name;
	String content;
	//final int startPos;
	//final int endPos;
	public Token(String name,String content){ //, int startPos , int endPos){
		this.name = name;
		this.content = content.toLowerCase();
		//this.startPos = startPos;
		//this.endPos = endPos;
	}
	public String getContent(){
		return this.content;
	}
	public boolean equals(Token t2){
		if (t2 == null){
			return false;
		}
		return (this.content == t2.content );
		
	}
	@Override
    public String toString()
    {
	      return String.format("Token [%s, type: %s]", content, name);
      //return String.format("Token [%2d, %2d, %s]", startPos, endPos, name);
    }
}