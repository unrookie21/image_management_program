

public class Image {
	//객체 Image 는 (image id, image 파일 이름, image tags) 로 구성됨.
	
		// instance 변수
	 	private String imageId;
	    private String imageFileName;
	    private String[] imageTags;
	    
	    
	    // 이미지 생성자
	    
	    public Image(String imageId, String imageFileName, String[] imageTags) 
	    {
	        this.imageId = imageId;
	        this.imageFileName = imageFileName;
	        this.imageTags = imageTags != null ? imageTags : new String[0];
	    }

	    
	    // 이미지ID 가져오기
	    public String getImageId()
	    {
	    	return this.imageId;
	    	
	    }
	    
	    // 이미지fILE NAME 가져오기
	    public String getImageFileName()
	    {
	    	return this.imageFileName;
	    	
	    }
	    
	    // 이미지 TAGS 가져오기
	    public String[] getimageTags()
	    {
	    	
	    	return this.imageTags;
	    }
	    

}
