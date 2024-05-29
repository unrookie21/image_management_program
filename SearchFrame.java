import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class SearchFrame extends JFrame 
{
    
	private JTextField fromField;
    private JTextField toField;
    private JTextField tagsField;
    private JTextField commentsField;
    private JTextField searchTextField;
    private StuffPanel stuffPanel_Search;
    private PictureList pictureList;
    private PictureSearchFrame pictureSearchFrame;
    
	
    public SearchFrame(PictureList pictureList, PictureSearchFrame pictureSearchFrame)
    
    {
    	
    	this.pictureList = pictureList;
    	this.pictureSearchFrame =pictureSearchFrame;
        
    	setTitle("Search Picture");
        setSize(600, 400);
        
        
        // 큰 컨텐츠 팬 만들기
        Container contentPane = getContentPane();
        
        // 상단 팬 만들기 
        JPanel upperPanel = new JPanel(new GridLayout(2, 3));
        
        TitledBorder upperBorder = new TitledBorder("Time Search");
        upperPanel.setBorder(upperBorder);
        
        
        JLabel fromLabel = new JLabel("From");
        JLabel toLabel = new JLabel("To");
        fromField = new JTextField();
        toField = new JTextField();
        JLabel yyyy = new JLabel("yyyy-MM-dd_HH:mm:ss");
        
        
        upperPanel.add(fromLabel);
        upperPanel.add(fromField);
        upperPanel.add(yyyy);
        upperPanel.add(toLabel);
        upperPanel.add(toField);
        
        // 컨텐츠 팬에 상단 팬 추가
        
        contentPane.add(upperPanel, BorderLayout.NORTH);
        
        
        // 중간 팬 만들기
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        TitledBorder centerBorder = new TitledBorder("Keyword Search");
        centerPanel.setBorder(centerBorder);
        
        // 중간 왼쪽 팬 만들기 
        
        JPanel centerLeftPanel = new JPanel(new GridLayout(2, 2));
        JLabel tagsLabel = new JLabel("Tags");
        JLabel commentsLabel = new JLabel("Comments");
        tagsField = new JTextField();
        commentsField = new JTextField();
        
        
        tagsField.setPreferredSize(new Dimension(200, 25)); // 크기 설정
        commentsField.setPreferredSize(new Dimension(200, 25)); // 크기 설정
        
        
        centerLeftPanel.add(tagsLabel);
        centerLeftPanel.add(tagsField);
        centerLeftPanel.add(commentsLabel);
        centerLeftPanel.add(commentsField);
        
        
        centerPanel.add(centerLeftPanel, BorderLayout.WEST);
        
        // 중간 오른쪽 팬 만들기 
        
        stuffPanel_Search = new StuffPanel(); // 기존에 sutffpanel 을 일일이 작성한 것과 비교해서, 코드가 매우 줄었음
        // stuffPanel 은 searchframe 뿐만 아니라 addframe 에서도 등장하므로 정보 단위로 그룹화 해서 StuffPanel 이라는 클래스를 따로
        // 만들어준것임. 
        
        
        // 센터팬 중간에 StuffPanel 추가
        centerPanel.add(stuffPanel_Search.getstuffPane(), BorderLayout.CENTER);
        
        // 중간 하단 팬 만들기
        
        JPanel centerLowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel generalSearchLabel = new JLabel("General Search");
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200, 25)); // JTextField 크기 설정
        
        
        centerLowerPanel.add(generalSearchLabel);
        centerLowerPanel.add(searchTextField);
        
        
        centerPanel.add(centerLowerPanel, BorderLayout.SOUTH);
        
        
        // 컨텐츠 팬 중간에 중간 팬 삽입
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        
        // 하단 팬 만들기
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton andSearchButton = new JButton("AND SEARCH");
        andSearchButton.addActionListener(new AndSearchListenerClass(pictureList, pictureSearchFrame, this));
        JButton orSearchButton = new JButton("OR SEARCH");
        orSearchButton.addActionListener(new OrSearchListenerClass(pictureList, pictureSearchFrame, this));
        JButton closeButton = new JButton("CLOSE");
        closeButton.addActionListener(new CloseListenerClass(this));
        
        
        lowerPanel.add(andSearchButton);
        lowerPanel.add(orSearchButton);
        lowerPanel.add(closeButton);
        
        
        // 하단 팬을 컨첸츠 팬에 추가
        contentPane.add(lowerPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    public String getFromTime() {
        return fromField.getText().trim();
    }

    public String getToTime() {
        return toField.getText().trim();
    }

    public String getTags() {
        return tagsField.getText().trim();
    }

    public String getComments() {
        return commentsField.getText().trim();
    }

    public StuffPanel getStuffPanel() {
        return stuffPanel_Search;
    }

    
    public interface ActionListener extends EventListener{
    	
		void actionPerformed(ActionEvent e);
    }



}
