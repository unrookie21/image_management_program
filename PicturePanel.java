import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


// PicturePanel 은 Picture 정보를 가지고 있어야한다. Picture 를 바탕으로 PicturePanel 이 만들어지기 때문에. 
public class PicturePanel extends JPanel {

	private Picture picture;
	
    // PicturePanel 생성자
    public PicturePanel(Picture picture) 
    {
    	this.picture = picture;
        // 제일 큰 픽쳐 팬 선언, 마지막에 여기다 다 추가해야됨
        JPanel picturePane = new JPanel();
        picturePane.setLayout(new BorderLayout());

        
        // 상단에 시간과 tag
        
        JPanel timeAndTagPane = new JPanel(); // 얘 마지막에 추가
        timeAndTagPane.setLayout(new BorderLayout());

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
        String formattedTime = picture.getTimestamp().format(formatter);
        JLabel pictureTime = new JLabel(formattedTime);

        
        
        //JLabel tagLabel = new JLabel(String.join(" ", picture.getTags())); // 이렇게 했을때 데이터가 3개밖에 출력이 안됬음
        
        // 수정한 부분 
        String[] tags = picture.getTags() != null ? picture.getTags() : new String[0];
        JLabel tagLabel = new JLabel(String.join(" ", tags));
        
        LineBorder tagBorder = new LineBorder(Color.lightGray);
        tagLabel.setBorder(tagBorder);
        

        timeAndTagPane.add(pictureTime, BorderLayout.WEST);
        timeAndTagPane.add(tagLabel, BorderLayout.EAST);

        // 큰 CENTERPANE 생성
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new BorderLayout());

        
        ImageIcon imageIcon = new ImageIcon(picture.getImageInfo().getImageFileName());
        JLabel imagelabel = new JLabel(imageIcon);
        
        
        
        imagelabel.setPreferredSize(new Dimension(200, 200));
        centerPane.add(imagelabel, BorderLayout.WEST);
        // 이미지는 센터패널의 왼쪽에 넣기
        
        JPanel centerRightPane = new JPanel();
        centerRightPane.setLayout(new GridLayout(0, 1));
        
        

        JScrollPane centerRightScrollPane = new JScrollPane(centerRightPane);
        centerRightScrollPane.setPreferredSize(new Dimension(200, 200));

        
        
        // stuff 의 개수 만큼 pane 을 생성
        for (Stuff stuff : picture.getStuffs()) 
        {
            // pane 생성 함수
        	StuffPanel mystuffPane = new StuffPanel(stuff);
         
        	centerRightPane.add(mystuffPane.getstuffPane());
        }
        

        centerPane.add(centerRightScrollPane, BorderLayout.CENTER);

        
        
        // 하단에 comment
        
        JPanel belowPane = new JPanel();
        belowPane.setLayout(new BorderLayout());
        LineBorder lineBorder = new LineBorder(Color.gray, 5);
        belowPane.setBorder(lineBorder);

        
        
        JLabel commentLabel = new JLabel();
        if (picture.getComment() != null && !picture.getComment().isEmpty()) 
        {
            commentLabel.setText(picture.getComment());
        }
        belowPane.add(commentLabel, BorderLayout.WEST);
        

        
        
        picturePane.add(timeAndTagPane, BorderLayout.NORTH);
        picturePane.add(centerPane, BorderLayout.CENTER);
        picturePane.add(belowPane, BorderLayout.SOUTH);

        
        
        this.setLayout(new BorderLayout());
        this.add(picturePane, BorderLayout.CENTER);
    }
    
    public Picture getPicture()
    {
    	return picture;
    }
    
    

}