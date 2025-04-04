import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


// AddFrame 에서 사용자의 입력을 바탕으로 Picture 객체를 만들고, Picture Panel 을 만들때 ,
// Picture 객체의 "Image" 정보는 이 클래스에서 만들어져서 addFrame 에 전달됨.
// 즉, 사용자가 JFILECHOOSER 를 통해 이미지를 선택하면, 그 이미지에 대한 정보를 추출하여 Image 인스턴스를 생성하고, addFrame 에 전달.


class SelectImageFileListener implements ActionListener
{
	private AddFrame addFrame;
	
	public SelectImageFileListener(AddFrame addFrame) 
	{
        this.addFrame = addFrame;
    }
	
	public void actionPerformed(ActionEvent e) // Select Image File 버튼이 클릭되었을경우 아래 동작 수행 
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		String formattedTime = now.format(formatter);
		
		String imageID = "IMG" + formattedTime;
		
		
		JFileChooser fileChooser = new JFileChooser();
		
		int result = fileChooser.showOpenDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			String imageFileName = "images/" + selectedFile.getName();
			Image image = new Image(imageID, imageFileName, null);
			addFrame.setImage(image);
			
			
		}
			
		
		
	}
	
	public Image getImageInstance()
	{
		return addFrame.getImage();
	}
	
	
}