package mir1;


class Token{
	final String name;
	String content;
	//final int startPos;
	//final int endPos;
	Token(String name,String content){ //, int startPos , int endPos){
		this.name = name;
		this.content = content.toLowerCase();
		//this.startPos = startPos;
		//this.endPos = endPos;
	}
	
	@Override
    public String toString()
    {
	      return String.format("Token [%s, type: %s]", content, name);
      //return String.format("Token [%2d, %2d, %s]", startPos, endPos, name);
    }
}