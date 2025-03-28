

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Picture {
	
	
		private String ID;
	    private LocalDateTime timestamp;
	    private Image imageInfo;
	    private Stuff[] stuffs;
	    private String[] tags;
	    private String comment;
	    

	    public Picture(String ID, LocalDateTime timestamp, Image imageInfo, Stuff[] stuffs, String[] tags, String comment) {

	    	this.ID = ID;
	        this.timestamp = timestamp;
	        this.imageInfo = imageInfo;
	        this.stuffs = (stuffs != null) ? stuffs : new Stuff[0]; // null일 경우 빈 배열로 초기화
	        this.tags = tags;
	        this.comment = comment;
	    }
	    
	    // 생성자 인자로 image 파일 이름 주면 , 현재 시각으로 timestamp 및 ID 만드는 생성자
	    
	    public Picture(String imageFileName)
	    {
	    	LocalDateTime now = LocalDateTime.now();
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
	    	
	    	String formattedDateTime = now.format(formatter);
	    	this.ID = formattedDateTime;
	    	
	    	LocalDateTime localTimeObj1 = LocalDateTime.parse(formattedDateTime, formatter);
	    	this.timestamp = localTimeObj1;

	    	// ID 에는 String 넣어야되고, timestamp 에는 LocalDatetime 형식으로 넣어야함
	    	
	    }
	    
	    public String getID()
	    {
	    	return this.ID;
	    	
	    }
	    
	    public LocalDateTime getTimestamp()
	    {
	    	return timestamp;
	    }
	    
	    public Stuff[] getStuffs()
	    {
	    	return stuffs;
	    }
	    
	    public Image getImageInfo() {
	        return this.imageInfo; 
	    }
	    
	    public Stuff getStuff(int i)
	    {
	    	return stuffs[i];
	    }
	    
	    public String[] getTags()
	    {
	    	return tags;
	    }
	    
	    public String getComment()
	    {
	    	return comment;
	    }
	    
	    // 하나의 사진에 대한 정보를 콘솔로 출력
	    
	    public void print() 
	    {
	        System.out.print("< " + this.ID + " > < " + this.timestamp + " > < ");
	        
	        System.out.print(this.imageInfo.getImageId() + "; " + this.imageInfo.getImageFileName());
	        
	        if (this.imageInfo.getimageTags().length > 0) 
	        {
	            System.out.print("; " + String.join(" ", this.imageInfo.getimageTags()));
	        }
	        
	        System.out.print(" > < ");

	        if (this.stuffs != null && this.stuffs.length > 0) 
	        {
	            System.out.print("[");
	            
	            for (int i = 0; i < this.stuffs.length; i++)
	            {
	                Stuff stuff = this.stuffs[i];
	                
	                System.out.print(stuff.getID() + "; " + stuff.getType() + "; " + stuff.getName());
	                
	                if (stuff.getStuffTags().length > 0) 
	                {
	                    System.out.print("; " + String.join(" ", stuff.getStuffTags()));
	                }
	                
	                if (i < this.stuffs.length - 1)
	                {
	                    System.out.print(" ] [ ");
	                }
	                
	            }
	            
	            System.out.print("]");
	        }
	        
	        System.out.print(" > ");

	        System.out.print("<");
	        if (this.tags != null && this.tags.length > 0) 
	        {
	            
	            System.out.print(String.join(" ", this.tags));
	            
	        }
	        System.out.print("> ");
	        
	        System.out.print("<");

	        if (this.comment != null && !this.comment.isEmpty()) 
	        {
	            System.out.print(this.comment);
	        }
	        System.out.print("> ");
	        System.out.println();
	    } 
	
}
