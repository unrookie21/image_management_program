import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// 사용자가 직접 정보를 입력하여 사진 정보를 PictureSearchFrame 의 normalPane 에 추가하는 기능 
public class AddFrame extends JFrame
{

	private JTextField timeTextField;
	private JTextField tagsTextField;
	private JTextField commentTextField;
	private ArrayList<StuffPanel> stuffPanels;
	private JPanel StoreStuffPane;
	private StuffPanel initialStuffPanel;
	
	public int storeStuffcount = 1;
	
	private Image myImage;
	
	private PictureList myPictureList;
	
	private PictureSearchFrame pictureSearchFrame;
	
	
    public AddFrame(PictureList pictureList, PictureSearchFrame pictureSearchFrame) 
    {
    	this.pictureSearchFrame = pictureSearchFrame;
    	this.stuffPanels = new ArrayList<StuffPanel>();
    	this.myPictureList = pictureList;
        setTitle("Add a Picture");
        setSize(600, 400);
        

        // 제일 큰 컨텐츠팬 하나 만들어 놓자
        
        Container contentPane = getContentPane();

        
        // 상단 팬 만들기
        
        JPanel upperPanel = new JPanel(new GridLayout(1, 2));
       
        
        // Time 라벨과 textfield
        
        JLabel timeLabel = new JLabel("Time");
        timeTextField = new JTextField();
        timeTextField.setPreferredSize(new Dimension(100, 30));
        

        JPanel timePanel = new JPanel(new BorderLayout());
        
        timePanel.add(timeLabel, BorderLayout.WEST);
        timePanel.add(timeTextField, BorderLayout.CENTER);
        

        
        // tags 라벨과 textfield
        
        JLabel tagsLabel = new JLabel("(Picture) Tags");
        tagsTextField = new JTextField();
        tagsTextField.setPreferredSize(new Dimension(100, 30));

        JPanel tagsPanel = new JPanel(new BorderLayout());
        
        tagsPanel.add(tagsLabel, BorderLayout.WEST);
        tagsPanel.add(tagsTextField, BorderLayout.CENTER);

        // timepanel 과 tagspanel 을 상단 패널에 추가
        
        upperPanel.add(timePanel);
        upperPanel.add(tagsPanel);

        
        // 상단 패널을 컨탠츠 팬의 north 에 추가
        
        contentPane.add(upperPanel, BorderLayout.NORTH);

        
        // 중간 팬 만들기 , borderlayout 으로
        // 구성요소는 EAST 에 TYPE, NAME, TAGS 로 구성된 팬, CENTER 에 SELECT IMAGE FILE 버튼, SOUTH 에 COMMENTS 와 텍스트필드로 구성된 팬
        JPanel centerPanel = new JPanel(new BorderLayout(10,10));
        
       

        JButton selectImageButton = new JButton("Select Image File");
        SelectImageFileListener selectImageButtonListener = new SelectImageFileListener(this);
        selectImageButton.addActionListener(selectImageButtonListener);
        
        myImage = selectImageButtonListener.getImageInstance();
        
        
        selectImageButton.setPreferredSize(new Dimension(250,200));
        initialStuffPanel = new StuffPanel(); //////////// 수정된 부분 
        stuffPanels.add(initialStuffPanel);
        		
        
       
        centerPanel.add(selectImageButton, BorderLayout.WEST);
        
        //centerPanel.add(stuffPanel_Add.getstuffPane(), BorderLayout.CENTER);
        
        
        // stuffPanel 을 모아두기 위한 JPanel 선언
        StoreStuffPane = new JPanel(new GridLayout(0,1));
        StoreStuffPane.add(initialStuffPanel.getstuffPane());
        JScrollPane scrollPane = new JScrollPane(StoreStuffPane);
        
        
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        

        // StuffPanel 클래스 안에 stuffPane 이라는 private 멤버변수가 있으므로,
        // 그냥 StuffPanel_Add 라 쓰면 안되고 뒤에 getter 붙여서 가져와야함.
        
        // 만약 AddFrame 에서 More Stuff 버튼 누르면 StuffPanel 하나 증가 시킨다. 
        
        
        JButton moreStuffButton = new JButton("More Stuff");
        MoreStuffListenerClass stuffbuttonListener = new MoreStuffListenerClass(this);
        
        // moreStuffButton 에 event 추가 
        moreStuffButton.addActionListener(stuffbuttonListener);
        
        JButton inputEndButton = new JButton("OK - INPUT END");
        InputEndListenerClass inputEndButtonListener = new InputEndListenerClass(this, myPictureList, pictureSearchFrame);
        // inputEndButton 에 event 추가
        inputEndButton.addActionListener(inputEndButtonListener);
        
        
        
        // comments label 과 textfield 만들기
        JPanel commentPanel = new JPanel(new BorderLayout());
        
        JLabel commentLabel = new JLabel("Comments");
        commentTextField = new JTextField();
        commentTextField.setPreferredSize(new Dimension(100,20));

        commentPanel.add(commentLabel, BorderLayout.WEST);
        commentPanel.add(commentTextField, BorderLayout.CENTER);

        centerPanel.add(commentPanel, BorderLayout.SOUTH);

        // CENTER 팬을 컨텐츠 팬 에 추가
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // 하단 팬
        JPanel lowerPanel = new JPanel(new BorderLayout(10,100));
        
        lowerPanel.setPreferredSize(new Dimension(800, 50));

        
        

        lowerPanel.add(moreStuffButton, BorderLayout.WEST);
        lowerPanel.add(inputEndButton, BorderLayout.EAST);
        
       

        
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(800, 100));

        // 하단 팬을 컨텐츠 팬에 추가
        contentPane.add(spacerPanel, BorderLayout.SOUTH);
        contentPane.add(lowerPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    
    }
    
    public ArrayList<StuffPanel> getStuffPanels(){
    	return stuffPanels;
    }
   
    public JPanel getStoreStuffPane() 
    {
        return StoreStuffPane;
    }
    
    
    public StuffPanel getInitialStuffPane()
    {
    	return initialStuffPanel;
    }
    
    public interface ActionListener extends EventListener{
    	
    		void actionPerformed(ActionEvent e);
    }
    
    public JTextField getTimeTextField()
    {
    	return timeTextField;
    }
    
    public JTextField getCommentTextField()
    {
    	return commentTextField;
    }
   
    public JTextField getTagsTextField() // 사진 태그임
    {
    	return tagsTextField;
    }
    
    public int getStoredStuffCount()
    {
    	return storeStuffcount;
    }
   
   
    public Image getImage()
    {
    	return myImage;
    }
    
    public void setImage(Image image)
    {
    	myImage = image;
    }
    
}








