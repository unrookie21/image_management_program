import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//SearchFrame 에서 사용자가 입력한 정보와 하나라도 일치하는 Picture 를 PictureList 에서 찾는 기능 
public class OrSearchListenerClass implements ActionListener 
{

    private PictureList pictureList;
    private PictureSearchFrame frame;
    private SearchFrame searchFrame;

    public OrSearchListenerClass(PictureList pictureList, PictureSearchFrame frame, SearchFrame searchFrame)
    {
    	
        this.pictureList = pictureList;
        this.frame = frame;
        this.searchFrame = searchFrame;
    }

    public void actionPerformed(ActionEvent e) 
    {
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
            boolean matches = false;

            // 시간 비교
            if (!fromTime.isEmpty() && !toTime.isEmpty())
            {
                LocalDateTime from = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
                LocalDateTime to = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
                LocalDateTime pictureTime = picture.getTimestamp();
                if (!pictureTime.isBefore(from) && !pictureTime.isAfter(to)) 
                {
                    matches = true;
                }
            }

            // 태그 비교
            if (!matches && !tags.isEmpty()) 
            {
                String[] pictureTags = picture.getTags();
                if (pictureTags != null) 
                {
                    for (String tag : pictureTags) 
                    {
                        if (tags.equals(tag)) 
                        {
                            matches = true;
                            break;
                        }
                    }
                }
            }

            // 코멘트 비교
            if (!matches && !comments.isEmpty()) 
            {
                String pictureComment = picture.getComment();
                if (pictureComment != null && pictureComment.contains(comments)) 
                {
                    matches = true;
                }
            }

            // StuffPanel 비교
            if (!matches) 
            {
                if (!stuffType.isEmpty() || !stuffName.isEmpty() || !stuffTags.isEmpty()) 
                {
                    Stuff[] pictureStuffs = picture.getStuffs();
                    for (Stuff stuff : pictureStuffs) {
                        if ((stuffType.isEmpty() || stuffType.equals(stuff.getType())) &&
                            (stuffName.isEmpty() || stuffName.equals(stuff.getName())) &&
                            (stuffTags.isEmpty() || String.join(" ", stuff.getStuffTags()).contains(stuffTags))) 
                        {
                            matches = true;
                            break;
                        }
                    }
                }
            }

            if (matches) 
            {
                searchResults.add(new PicturePanel(picture));
            }
        }

        frame.clearNormalPane();
        
        for (PicturePanel panel : searchResults) 
        {
            frame.addPicturePanel(panel);
        }
    }
}
