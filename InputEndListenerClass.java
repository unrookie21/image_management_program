import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// InputEnd 버튼이 눌렸을때의 기능 
// 1. StuffList 에 사용자가 입력한 stuff 정보 추가 (StuffList 는 DB)
// 2. 사용자가 입력한 정보 바탕으로 Picture 객체 생성 => PicturePanel(picture) => normalPane 에 PicturePanel 추가

// ★★★★★★★★★★★★★ 사용자의 입력이 InputEnd 되었을때 StuffList 에 Stuff 추가하는 기능 구현 필요 

class InputEndListenerClass implements ActionListener
{
	private AddFrame addFrame;
	private PictureList pictureList;
	public Stuff[] Stuffs;
	private PictureSearchFrame mainFrame;

	public InputEndListenerClass(AddFrame addFrame, PictureList pictureList, PictureSearchFrame mainFrame)
	{
	
		this.mainFrame = mainFrame;
		this.addFrame = addFrame;
		this.pictureList = pictureList;
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{ 
		
		
		ArrayList<StuffPanel> stuffPanels = addFrame.getStuffPanels();
		Stuffs = new Stuff[addFrame.storeStuffcount];
		
		//stuffList 에 stuff 추가하는 작업이라고 생각했는데 ㅋㅋ Stuff 객체 만들어서 Stuff 배열에 담는 작업이었던거임...
		// StuffList 에 Stuff 추가하는 부분은 추후 구현 해야함 
		for (int i = 0 ; i < addFrame.storeStuffcount; i++)
		{
			StuffPanel stuffPanel = stuffPanels.get(i);
			if (stuffPanel != null)
			{
				
				String Type = stuffPanel.getTypeRight().getText();
		        String Name = stuffPanel.getNameRight().getText();
		        String[] Tags = stuffPanel.getTagsRight().getText().split(" ");
		        
		       
		                
		        Stuff myStuff = new Stuff("", Type, Name, Tags);
	        	StuffList.checkandAdd(Type, Name, myStuff);
		        
	        	
		        
		        Stuffs[i] = myStuff;	
			}
			
			else 
			{
                System.err.println("Error: stuffPanel is null at index " + i);
            }
			
	       
		}
		
	
		String comments = addFrame.getCommentTextField().getText();
		String[] tags = addFrame.getTagsTextField().getText().split(" ");
		
		
	    // 이미지 정보를 생성해서 pictureList 에 기입 
		LocalDateTime Time;
		//String time = addFrame.getTimeTextField().getText().equals("") ? // 현재시간 :  addFrame.getTimeTextField().getText()
		
		if (addFrame.getTimeTextField().getText().equals("")) // timestamp 가 비어있다면 현재시간 입력 
		{
			LocalDateTime now = LocalDateTime.now();
	    	
	    	Time = now;
	    	
	    	
	    	
		}
		
		else // 시간을 직접 입력했다면 , 입력한 값을 할당 
		{
			
			String stringTime = addFrame.getTimeTextField().getText();
			
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
	    	
	    	Time = LocalDateTime.parse(stringTime, formatter);
	    	
		}
		
//		private String ID;
//	    private LocalDateTime timestamp;
//	    private Image imageInfo;
//	    private Stuff[] stuffs;
//	    private String[] tags;
//	    private String comment;
	   
		Image image = addFrame.getImage();
		
		if (image == null) 
		{
	            // 이미지가 설정되지 않았으면 오류 메시지 출력 또는 처리
	            System.err.println("Error: No image selected.");
	            return;
	        }
		
        Picture toAddPicture = new Picture("tobeentered", Time, image, Stuffs , tags, comments);

       // 이미지 정보는 "Select Image File" 이 클릭되고, 이미지가 입력되는 순간에 그 정보가 기입된다. 
        
        // toAddPicture 을 pictureList 에 넣어야함 
        
        mainFrame.addPicturePanel(new PicturePanel(toAddPicture));
        
        // Picture 배열
        
        // mainFrame 에 PicturePanel 을 추가하는 행위 = PictureSearchFrame 의 normalPane 에
        // PicturePanel 을 추가하는 행위 
        //writePictureToFile(toAddPicture, "picture-normal.data");
        
	}
	
//	private void writePictureToFile(Picture picture, String fileName) {
//	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
//	        String mTimestamp = "m_" + picture.getTimestamp().format(formatter);
//
//	        writer.write("< " + mTimestamp + " > < " + picture.getTimestamp().format(formatter) + " > < ");
//	        writer.write(picture.getImageInfo().getImageId() + "; " + picture.getImageInfo().getImageFileName() + "; ; > < ");
//
//	        if (picture.getStuffs() != null && picture.getStuffs().length > 0) 
//	        {
//	            writer.write("[");
//	            for (int i = 0; i < picture.getStuffs().length; i++) 
//	            {
//	                Stuff stuff = picture.getStuffs()[i];
//	                writer.write(stuff.getID() + "; " + stuff.getType() + "; " + stuff.getName());
//	                if (stuff.getStuffTags() != null && stuff.getStuffTags().length > 0) 
//	                {
//	                    writer.write("; " + String.join(" ", stuff.getStuffTags()));
//	                }
//	                if (i < picture.getStuffs().length - 1) 
//	                {
//	                    writer.write(" ] [ ");
//	                }
//	            }
//	            
//	            writer.write(" ]");
//	        }
//	        
//	        writer.write(" > <");
//
//	        if (picture.getTags() != null && picture.getTags().length > 0) {
//	            writer.write(String.join(" ", picture.getTags()));
//	        }
//	        writer.write(" > <");
//
//	        if (picture.getComment() != null && !picture.getComment().isEmpty()) {
//	            writer.write(picture.getComment());
//	        }
//	        
//	        writer.write(" > ");
//	        writer.newLine();
//	        
//	    } catch (IOException ex) {
//	        ex.printStackTrace();
//	    }
//	}
//	
//}
}