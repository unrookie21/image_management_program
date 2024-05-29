import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SearchListenerClass implements ActionListener{
	
	PictureList picturelist;
	PictureSearchFrame frame;
	
	public SearchListenerClass(PictureList pictureList, PictureSearchFrame pictureSearchFrame)
	
	{
		this.picturelist = pictureList;
		this.frame = pictureSearchFrame;
		
	}
	public void actionPerformed(ActionEvent e)
	{
		new SearchFrame(picturelist, frame);
		// SearchFrame 은 어떤 정보를 가지고 있어야하는지에 대한 고민부터 출발
	
	}

}