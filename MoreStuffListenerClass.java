import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

// AddFrame 에서 MORE STUFF 버튼을 눌렀을때의 동작
// 사용자가 입력할 수 있는 Stuff Panel 을 하나씩 증가시킨다. 

class MoreStuffListenerClass implements ActionListener
{
	private AddFrame addFrame;
	
	public MoreStuffListenerClass(AddFrame addFrame)
	{
		this.addFrame = addFrame;
	}
	public void actionPerformed(ActionEvent e)
	{ // more stuff 버튼을 눌렀을때 동작에 대한 코드 
		
		
		// 1. stuffPanel 을 하나 생성한다. 기본 생성자로 StuffPanel 생성
		StuffPanel stuffPanel = new StuffPanel();
		
		addFrame.getStuffPanels().add(stuffPanel);
		addFrame.storeStuffcount++;
		JPanel toAddStuffPane = stuffPanel.getstuffPane();
		// toAddStuffPane 을 AddFrame 의 중간 우측 패널에 추가해야함.
		// 즉, StoreStuffPane 에 add(toAddStuffPane) 을 해야함 
		addFrame.getStoreStuffPane().add(toAddStuffPane);
	    addFrame.getStoreStuffPane().revalidate();
	    addFrame.getStoreStuffPane().repaint();
	    

	}
}