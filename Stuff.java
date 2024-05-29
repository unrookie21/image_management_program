

public class Stuff {
	
		private String ID;
	    private String type;
	    private String name;
	    private String[] tags;

	    
	    // 생성자
	    
	    public Stuff(String ID, String type, String name, String[] tags) {
	        this.ID = ID;
	        this.type = type;
	        this.name = name;
	        this.tags = tags;
	    }
	    
	    // ID setter
	    public void setID(String ID)
	    {
	    	this.ID = ID;
	    	
	    }
	    
	    // ID getter
	    public String getID()
	    {
	    	return this.ID;
	    	    	
	    }
	    
	    // Type getter
	    public String getType()
	    {
	    	return this.type;	    	
	    }
	    
	    // Name getter
	    public String getName()
	    {
	    	return this.name;	    	
	    }
	    
	    // StuffTags getter
	    public String[] getStuffTags()
	    {
	    	return this.tags;	
	    }
	    
	    

}
