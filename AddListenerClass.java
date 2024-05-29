import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddListenerClass implements ActionListener{
	
	private PictureSearchFrame frame;

    public AddListenerClass(PictureSearchFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        new AddFrame(frame.getPictureList(), frame);
    }
	

}
