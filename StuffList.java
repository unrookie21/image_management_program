import java.util.ArrayList;

public class StuffList {

    private static ArrayList<Stuff> stuffList =  new ArrayList<Stuff>(300);;
    private static int count = 0;

    
    public static void checkandAdd(String type, String name, Stuff newStuff)
    {
    	Stuff existingStuff = findExistingStuff(type, name);
    	
    	if (existingStuff == null)
    	{
    		newStuff.setID(generateNewID());
    		stuffList.add(newStuff);
    		count++;
    		
    	}
    	
    	else // 사물이 존재하던 사물인 경우 
    	{
    		newStuff.setID(existingStuff.getID());
		}
    }
    
    
    public static void updateWithNewPicture(Picture picture) 
    {
        if (picture.getStuffs() == null) 
        	// 사진 객체의 Stuff 정보가 없으면, updateWithNewPicture 실행 할 필요가 없으므로 바로 return
        	 
        	return;

        for (Stuff newStuff : picture.getStuffs()) // 사진객체의 Stuffs[] 를 순회하며 Stuff 를 가져옴
        {
            if (newStuff == null) continue;

            Stuff existingStuff = findExistingStuff(newStuff.getType(), newStuff.getName());
            
            if (existingStuff == null) // 사물이 새로운 사물일 경우
            {
                newStuff.setID(generateNewID()); // 새로운 ID를 할당
                
                stuffList.add(newStuff);
                count++;
                
                
            }
            
            else // 사물이 기존에 존재하던 사물일 경우
            {
            	newStuff.setID(existingStuff.getID());
                // 기존에 존재하던 사물의 ID를 가져와서 할당
            	// 추가하고자 하는 사물이 기존에 존재하는 사물이면 따로 StuffList 에 추가하는 작업은 하지 않는다.
            }
        }
    }

    // 기존 Stuff 객체를 찾는 메소드
    public static Stuff findExistingStuff(String type, String name) 
    {
        for (int i = 0; i < count; i++)  // stufList 를 순회하면서
        {
        	if(stuffList.get(i).getType().equals(type) && stuffList.get(i).getName().equals(name))
        	{
        		return stuffList.get(i);
        	}
        	

        }
        return null;
    }

    
    // 새로운 Stuff ID를 생성하는 메소드
    public static String generateNewID() 
    {
        return String.format("%08d", count + 1);
    } // 00000001 , 00000002 , 00000003, ....... 이런식으로 할당

    
    
    // StuffList의 사물 정보를 출력하는 메소드
    public static void print() 
    {

        for (int i = 0; i < count; i++) 
        {
            if (stuffList.get(i) != null) 
            {
                System.out.print("[" + stuffList.get(i).getID() + "; " + stuffList.get(i).getType() + "; ");
                System.out.print((stuffList.get(i).getName() != null && !stuffList.get(i).getName().isEmpty()) ? stuffList.get(i).getName() : "");
                System.out.print("; ");
                String[] tags = stuffList.get(i).getStuffTags();
                
                if (tags != null && tags.length > 0)
                {
                    System.out.print(String.join(" ", tags)); // tags 배열에서 원소들을 가져와서 공백 추가하고 다 합침
                }
                
                System.out.println(" ]");
            }
        }
    }
}
