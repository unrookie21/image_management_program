import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



// SearchFrame 에서 사용자가 입력한 정보와 "모두" 일치하는 Picture 를 PictureList 에서 찾는 기능 
public class AndSearchListenerClass implements ActionListener {

    private PictureList pictureList;
    private PictureSearchFrame frame;
    private SearchFrame searchFrame;

    public AndSearchListenerClass(PictureList pictureList, PictureSearchFrame frame, SearchFrame searchFrame) 
    {
        this.pictureList = pictureList;
        this.frame = frame;
        this.searchFrame = searchFrame;
        
    }

    public void actionPerformed(ActionEvent e) {
    	 // and search button 이 클릭되는 경우 동작 
         // 기존에 표시된 사진 제거
    	
    	
        ArrayList<PicturePanel> searchResults = new ArrayList<>();

        String fromTime = searchFrame.getFromTime();
        String toTime = searchFrame.getToTime();
        String tags = searchFrame.getTags();
        String comments = searchFrame.getComments();
        String stuffType = searchFrame.getStuffPanel().getTypeRight().getText().trim();
        String stuffName = searchFrame.getStuffPanel().getNameRight().getText().trim();
        String stuffTags = searchFrame.getStuffPanel().getTagsRight().getText().trim();

        for (int i = 0; i < pictureList.size(); i++) 
        {
            Picture picture = pictureList.get(i);
            boolean matches = true;

            // 시간 비교
            if (!fromTime.isEmpty() && !toTime.isEmpty()) 
            {
                LocalDateTime from = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
                LocalDateTime to = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
                LocalDateTime pictureTime = picture.getTimestamp();
                
                if (pictureTime.isBefore(from) || pictureTime.isAfter(to))
                {
                    matches = false;
                }
            }

            // 태그 비교
            if (matches && !tags.isEmpty())
            {
                String[] pictureTags = picture.getTags();
                
                if (pictureTags != null) 
                {
                    boolean tagMatch = false;
                    for (String tag : pictureTags) 
                    {
                        if (tags.equals(tag)) 
                        {
                            tagMatch = true;
                            break;
                        }
                    }
                    
                    if (!tagMatch) 
                    {
                        matches = false;
                    }
                } 
                else 
                {
                    matches = false;
                }
            }

            // 코멘트 비교
            if (matches && !comments.isEmpty())
            {
                String pictureComment = picture.getComment();
                if (pictureComment == null || !pictureComment.contains(comments)) 
                {
                    matches = false;
                }
            }

            // StuffPanel 비교
            if (matches) 
            {
                if (!stuffType.isEmpty() || !stuffName.isEmpty() || !stuffTags.isEmpty())
                {
                    Stuff[] pictureStuffs = picture.getStuffs();
                    boolean stuffMatch = false;
                    for (Stuff stuff : pictureStuffs) 
                    {
                        if ((stuffType.isEmpty() || stuffType.equals(stuff.getType())) &&
                            (stuffName.isEmpty() || stuffName.equals(stuff.getName())) &&
                            (stuffTags.isEmpty() || String.join(" ", stuff.getStuffTags()).contains(stuffTags))) 
                        {
                            stuffMatch = true;
                            break;
                        }
                    }
                    
                    if (!stuffMatch) {
                    	
                        matches = false;
                    }
                }
            }

            if (matches) {
                searchResults.add(new PicturePanel(picture));
            }
        }
        
        frame.clearNormalPane();

        for (PicturePanel panel : searchResults) {
            frame.addPicturePanel(panel);
        }
    }
}