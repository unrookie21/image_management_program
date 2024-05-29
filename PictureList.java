
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javax.swing.JPanel;


public class PictureList {
	

	private ArrayList<Picture> pictureList; // 변경사항
    private int count;
    private PictureSection picSection; // 추가 2
    
    
    //기본 생성자 
    PictureList() 
    {
    	this.pictureList = new ArrayList<Picture>(100);
    	
       // this.pictureList = new Picture[100];
        this.count = 0;
        this.picSection = new PictureSection();
    }
    
    public PictureList(String intoFileName)
    {
    	this();
    	System.out.println("Loading from file: " + intoFileName); // 디버깅 출력
    	loadFromFile(intoFileName);
    	System.out.println("Loaded " + count + " pictures from file."); // 디버깅 출력
    	
    }
    
    // 인자로 파일 이름을 주면 해당 파일에 기술된 사진 정보들로 리스트를 만드는 생성자
    public void loadFromFile(String intoFileName) 
    {
         // PictureList() 생성자 호출
        
        File file = new File(intoFileName);
        
        try (Scanner input = new Scanner(file)) // input 이라는 Scanner 만들어서 file 을 읽도록 만듦.
        {
            while (input.hasNextLine()) // input 이 다음줄을 가지고 있다면
            {
                String line = input.nextLine().trim(); // input 다음줄을 line 에 넣음 
                System.out.println("Reading line: " + line);  // 디버깅용 출력
                if (!line.isEmpty() && !line.startsWith("//")) 
                {
                    try 
                    {
                        Picture picture = parsePictureFromLine(line);
                        if (picture != null) 
                        {
                        	System.out.println("Parsed picture: " + picture);  // 디버깅용 출력 추가
                            // 중복 ID 검사
                            boolean isDuplicate = false;
                            
                            for (int i = 0; i < count; i++) 
                            {
                                if (pictureList.get(i).getID().equals(picture.getID()))
                                	// 같은 Picture ID 를 가진 사진 정보가 들어오면,
                                {
                                    System.err.println("ID Conflict (a picture with the same ID already exists); Skip the input line: " + line);
                                    isDuplicate = true;
                                    // isDuplicate Flag 를 true 로 만들고 for 문 탈출
                                    break;
                                    

                                }
                            }
                            
                            if (!isDuplicate) // 중복된 picture ID 가 아니라면, 
                            {
                                add(picture); // PictureList 에 picture 정보를 추가 
                                StuffList.updateWithNewPicture(picture); // 동시에 StuffList 의 메소드에 picture 전달
                                
                                
                 
                         
                            }
                            
                            //JPanel tempPane = picSection.picPanesfromSection();
                            //frame.addPictureSection(tempPane);
                        }
                    }
                    catch (Exception e) 
                    {
                    	e.printStackTrace(); // 디버깅용	
                    	break; // 어떤 오류든간에, 오류가 발생하면 더 이상 다음 입력을 받지 않고 while 문을 깨고 나온다.
                    }
                }
            }
            
        } 
        catch (FileNotFoundException e) // input 이 파일에서 정보를 잘못 읽어왔을때 나는 오류  
        {
            System.err.println("Unknown Picture Info File");
            System.exit(1); // 프로그램 강제 종료
        }
        
     
        
    }

    public PictureSection getPictureSection()
    {
    	return picSection;
    }

    // 리스트의 끝에 사진 정보 추가(PictureList 에도 넣고, 동시에 PictureSection 에도 넣으면?)
    public void add(Picture pic) 
    {
        pictureList.add(pic);
        count++;
        picSection.addPicturePanel(new PicturePanel((pic)));
        System.out.println("Added picture to PictureList and PictureSection: " + pic); // 디버깅 출력 
        
        
    }
    
    public void remove(Picture pic) {
        if (pictureList.contains(pic)) {
            pictureList.remove(pic);
            count--;
        }
    }
   
    
    
    public int size() 
    {
        return count;
    }
    


    public Picture get(int i) 
    {
        if (i >= 0 && i < count) 
        {
            return pictureList.get(i);
        }
        
        return null;
    }
    
    // 모든 사진 / 사물 정보들을 timestamp 시간 순으로 정렬
    
    public void sortByDate() // pictureList 가 배열에서 arrayList 로 바뀌면서 Arrays.sort -> Collections.sort 로 변경
    {
        Collections.sort(pictureList, new Comparator<Picture>() 
        {
            public int compare(Picture p1, Picture p2) 
            {
                return p1.getTimestamp().compareTo(p2.getTimestamp());
            }
            
        });
    }

    
    
    
    
    // PictureList 내부의 모든 Picture의 정보출력
    public void printAllPicture()
    {
    	  for (int i = 0; i < count; i++)
          {
    		  
       	   Picture printPicture = this.get(i);
       	   printPicture.print();
          }
    	
    	
    	
    }
    
    // * 은 앞에 공백이 있을 수도 있고 없을 수도 있음을 의미
    // ^는 문자열의 시작을 의미
    // $는 문자열의 끝을 의미
    
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
			comment = null;
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
    
    
    
    // 나머지 메소드 구현 (생략)
}