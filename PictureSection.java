import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PictureSection extends JPanel { // PictureList 에 대응하는 GUI 클래스 

	private ArrayList<PicturePanel> picturePanels;
	
	// 여러가지 PicturePanel 을 담아야한다. 그리고 이를 arraylist 에 저장
	// 만약에 GUI 에서 ADD 나 DELETE 를 통해서 PicturePanel 이 추가되거나, 사라진다면
	// PictureSection 의 arrayList 의 원소가 당연히 바뀌어야하고,
	// 파일에 저장되어 있는 데이터의 목록도 수정이 되어야함.
	// 이런식으로 PictureSection 과 Gui , PictureList 사이의 호환성을 구현하면 될 듯 
	
	public PictureSection()//PictureList pictureList)
	{
		this.picturePanels = new ArrayList<PicturePanel>(100);
	}
	
	
	public void addPicturePanel(PicturePanel picPane) // 
	{
			picturePanels.add(picPane);
			System.out.println("Added PicturePanel: " + picPane); // 디버깅 출력
	}
	
	public void picturePanelDelte(PicturePanel picPane)
	{
			picturePanels.remove(picPane);
	}
	
	public ArrayList<PicturePanel> getPictureSection()
	{
		return picturePanels;
	}
	
	// picturePanels 에 있는 picturePanel 들을 한 패널 에 다 넣고, 그 패널 을 반환해주는 함수
	
	public JPanel getPictureSectionPanel()
	{
		JPanel pane = new JPanel(new GridLayout(0,1));
		
		for (PicturePanel picturePanel : picturePanels) {
			
			pane.add(picturePanel);
		}
	
        System.out.println("Returning PictureSectionPanel with " + picturePanels.size() + " panels."); // 디버깅 출력
		return pane;
		
	}
	
	
	
	
	
	
}
