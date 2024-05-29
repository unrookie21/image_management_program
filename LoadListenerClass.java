import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JFileChooser;


// load 버튼 기능
// 1. load 버튼 클릭하면 File Chooser 띄워서 사용자가 파일을 선택하게 함
// 2. 현재 메모리에 있는 picture 와는 상관없이 사용자가 선택한 파일로부터 pictureList 를 새로 만들고, normalPane 에 띄운다. 

// ★★★★★★★★★★★★★★ 수정해야 할 사항 : main 에서 프로그램 실행 후 , load 버튼 눌러서 파일을 가져오면
// 어떤 파일이든간에 상관없이 "comments" 부분에 ">" 얘가 추가되어서 출력이 됨. 이 부분이 버그임... 
// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

public class LoadListenerClass implements ActionListener{
	
	private PictureSearchFrame frame;
	private String fileName;
	
	public LoadListenerClass(PictureSearchFrame frame)
	{
		this.frame = frame;
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(frame);
		
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			try 
			{
				loadPicturesFromFile(selectedFile);
			}
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//“LOAD” 버튼을 누르면, 현재 상태, 즉, 메모리에 있는 picture 정보에 상관없이, 파일에 있는
		//picture 정보들로 메모리의 picture list를 새로 구축한다. 당연히 화면도 파일에 있는 picture
		//정보들로 새로 표시되어야 한다.
		//Load를 누르면 filechooser 가 떠야한다.
		// PictureList("선택된 파일 이름") 으로 생성되어야 한다. 
		
		// Load 버튼을 누르면 , normalPane 을 "갱신해야한다."
		// PictureSearchFrame 의 normalPane 을 가져와서,
		// normalPane.revalidate();
		// normalPane.repaint();
		
		
		
		
		
		
	}
	
	private void loadPicturesFromFile(File file) throws Exception 
	{
		
		
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            frame.clearNormalPane(); // normalPane의 기존 컴포넌트 제거
            PictureList newPictureList = new PictureList(file.getPath());
            fileName = file.getPath();
            
            while ((line = reader.readLine()) != null) 
            {
        		if (!line.isEmpty() && !line.startsWith("//"))
        		{
        			Picture picture = parsePictureFromLine(line);
                    if (picture != null) 
                    {
                    	newPictureList.add(picture);
                        frame.addPicturePanel(new PicturePanel(picture));
                    }	
        		}
                
            }
         
            
            
            frame.setPictureList(newPictureList, fileName); // 새로운 PictureList 설정
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
	
	public String getPresentFileName()
	{
		return fileName;
	}
	
	public Picture parsePictureFromLine(String line) throws Exception 
    // throws 자신을 호출하는 메소드에 예외처리 책임을 떠넘기는 거
    { 
    	String[] parts = line.split(" *> *< *");
        
        if (parts.length < 3) 
        {
            throw new IllegalArgumentException("Insufficient data in line: " + line);
            // throw 는 강제로 예외 발생시키는거임
        }
        
        parts[0] = parts[0].replaceAll("^< *", "");
	    parts[parts.length - 1] = parts[parts.length - 1].replaceAll(" *>$", "");
	    
        
        String ID = parts[0].trim();
        LocalDateTime timestamp;
        
        if (parts[1].trim().isEmpty())
        {
            System.err.println("No time stamp in the input: " + line);
            throw new IllegalArgumentException("No time stamp");
        }
        
        try 
        {
            timestamp = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
        } 
        
        
        catch (DateTimeParseException e) // 얘는 parse 하다가 예외가 생긴거니까, 한번더 예외를 던져야
        // parsePictureFromLine 에서 예외가 난 것으로 처리 
        {
            System.err.println("Wrong DateTime Format: " + parts[1].trim());
            throw e;
            // 강제로 예외 발생 -> throws Exception 으로 전달 -> parsePictureFromline 호출한 곳의 catch 로 이동해서 예외처리
        }
        
        String[] imageParts = parts[2].split("; *");
	    
	    if (imageParts.length < 2)
	    { // 이미지 정보가 충분하지 않은 경우
	        return null;
	    }
	    
	    Image image = new Image(imageParts[0].trim(), imageParts[1].trim(),
	                            imageParts.length > 2 ? imageParts[2].trim().split(" ") : new String[0]);

	    Stuff[] stuffs = null;
	    String[] tags = null;
	    String comment = null;

	    if (parts.length > 3 && !parts[3].trim().isEmpty()) 
	    {
	        stuffs = parseStuffs(parts[3].trim());
	    }
	    
	    if (parts.length > 4 && !parts[4].trim().isEmpty()) 
	    {
	        tags = parts[4].trim().split(" +");
	    }
	    
	    if (parts.length > 5 && !parts[5].trim().isEmpty()) 
	    {
	        comment = parts[5].trim();
	    }
	    else {
            comment = null; // comment가 비어있을 경우 null로 설정합니다.
        }

        

        return new Picture(ID, timestamp, image, stuffs, tags, comment);
    }

    // Picture 클래스 및 기타 필요한 메소드 구현...

    
    public Stuff[] parseStuffs(String stuffsStr)
    {
        
        String[] stuffParts = stuffsStr.substring(1, stuffsStr.length() - 1).split("\\] \\[");
        Stuff[] stuffs = new Stuff[stuffParts.length];

        for (int i = 0; i < stuffParts.length; i++) 
        {
            String[] details = stuffParts[i].split("; ");

            
            String id = details[0].trim();
            String type = details.length > 1 ? details[1].trim() : "";
            String name = details.length > 2 ? details[2].trim() : "";
            String[] tags = details.length > 3 ? details[3].trim().split(" ") : new String[0];
            
            stuffs[i] = new Stuff(id, type, name, tags);
        }

        return stuffs;
    }
    
    
 
}


