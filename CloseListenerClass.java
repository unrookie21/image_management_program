import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// SearchFrame 에서 CLOSE 버튼 누르면 SearchFrame 닫게 해주는 기능 
public class CloseListenerClass implements ActionListener{
	
	private SearchFrame frame;
	
	public CloseListenerClass(SearchFrame frame)
	{
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		frame.dispose();
		
	}
}
