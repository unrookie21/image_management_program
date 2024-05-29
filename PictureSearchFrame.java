import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventListener;

public class PictureSearchFrame extends JFrame {

    private JPanel normalPane; // 사진이 추가되는 핵심 부분 
    private JScrollPane scrollPane; // normalPane 에 스크롤만 단거임
    private PictureList pictureList;
    private String currentFileName; // 현재 어떤 파일이 열려있는지 확인하기 위해 
    
    

    
    
    // 생성자
    public PictureSearchFrame() 
    {
        setTitle("Simple Picture Search");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pictureList = new PictureList("picture-normal.data");
        currentFileName = "picture-normal.data";
        // 여기서 옵저버 등록?
        // 세 컨텐츠 팬을 담는 하나의 거대한 컨텐츠 팬 선언, BorderLayout으로 선언
        
        Container totalContentPane = getContentPane();
        totalContentPane.setLayout(new BorderLayout());

        // 맨 위에 Show All pictures 버튼 하나 만들어 놓자
        
        JButton showPictureButton = new JButton("Show All Pictures"); 
        
        totalContentPane.add(showPictureButton, BorderLayout.NORTH);

        // 큰 컨텐츠 팬의 EAST에 위치할 5 x 1 GridLayout을 만들자
        
        JPanel varietyButton = new JPanel(new GridLayout(5, 1));
        

        // 5개의 주요 버튼 생성
        
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new AddListenerClass(this)); // add 버튼에 이벤트 먹이기 
        
        JButton deleteButton = new JButton("DELETE");
        JButton loadButton = new JButton("LOAD");
        LoadListenerClass loadButtonListener = new LoadListenerClass(this);
        
        loadButton.addActionListener(loadButtonListener);
        String presentFile = loadButtonListener.getPresentFileName();
        
        
        JButton saveButton = new JButton("SAVE");
        //SaveListenerClass saveButtonListener = new SaveListenerClass();
        //saveButton.addActionListener(saveButtonListener);
        saveButton.addActionListener(new SaveListenerClass(this));
        // saveButton 에 event 추가
        
        
        
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(new SearchListenerClass(pictureList,this)); // search 버튼에 이벤트 먹이기

        // 5개의 버튼을 GridLayout에 추가
        JButton[] buttons = new JButton[]{addButton, deleteButton, loadButton, saveButton, searchButton};
        for (JButton jButton : buttons) {
            varietyButton.add(jButton);
        }

        // 큰 컨텐츠 팬에 varietyButton 패널을 추가
        totalContentPane.add(varietyButton, BorderLayout.EAST);
        

        // normalPane 및 scrollPane 초기화
        
        ArrayList<PicturePanel> picturePanels =  pictureList.getPictureSection().getPictureSection();
        normalPane = new JPanel(new GridLayout(0,1));
        // PictureSection 에서 PicturePanel 하나씩 가져와서 normalPane 에 추가하기.
        for (int i = 0 ; i < picturePanels.size(); i++)
        {
        	normalPane.add(picturePanels.get(i));
        	
        }

        //normalPane.add(pictureList.getPictureSection().getPictureSectionPanel());
        scrollPane = new JScrollPane(normalPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // 스크롤바 항상 보이기
        
      


        
        totalContentPane.add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
        
        System.out.println("PictureSearchFrame initialized with " + normalPane.getComponentCount() + " components in normalPane."); // 디버깅 출력
        System.out.println("PictureList contains " + pictureList.size() + " pictures."); // 디버깅 출력
    }

    
    // PicturePanel을 normalPane에 추가하는 메서드
    
    public PictureList getPictureList()
    {
    	return pictureList;
    }

    public JPanel getNormalPane() {
        return normalPane;
    }
    
    public void clearNormalPane() {
        normalPane.removeAll();
        normalPane.revalidate();
        normalPane.repaint();
    }

    public String getCurrentFileName()
    {
    	return currentFileName;
    }
    
    public void setPictureList(PictureList pictureList, String fileName) {
        
        this.pictureList = pictureList;
        this.currentFileName = fileName;
        
    }
    
    public void addPicturePanel(PicturePanel panel) {
        normalPane.add(panel);
        normalPane.revalidate();
        normalPane.repaint();
    }

    
    public interface ActionListener extends EventListener{
    	
    	void actionPerformed(ActionEvent e);
    }
    
}




