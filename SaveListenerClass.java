import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

class SaveListenerClass implements ActionListener
{
	// normalPane 가져와야함
	 private PictureSearchFrame pictureSearchFrame;
	 
	 // 추후 normalPane 에 접근하기 위함. 
	 
	 public SaveListenerClass(PictureSearchFrame pictureSearchFrame)
	 {
		 this.pictureSearchFrame = pictureSearchFrame;
		 
	 }
	
	 public void actionPerformed(ActionEvent e) 
	 {
		 // pictureList 에도 추가해야되는가?
		 savePicturesToFile();
	 }
	
	 private void savePicturesToFile() 
	 {
		 	String fileName = pictureSearchFrame.getCurrentFileName();
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) 
	        {
	        	
	            for (int i = 0; i < pictureSearchFrame.getNormalPane().getComponentCount(); i++) 
	            {
	                if (pictureSearchFrame.getNormalPane().getComponent(i) instanceof PicturePanel) 
	                {
	                    Picture picture = ((PicturePanel) pictureSearchFrame.getNormalPane().getComponent(i)).getPicture();
	                    writePictureToFile(writer, picture);
	                }
	            }
	            System.out.println("Pictures saved to file " + fileName);
	            
	        } 
	        catch (IOException ex) 
	        {
	            ex.printStackTrace();
	        }
	        
	    }
	 
	 private void writePictureToFile(BufferedWriter writer, Picture picture) throws IOException 
	 {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
	        String mTimestamp = "m_" + picture.getTimestamp().format(formatter);

	        writer.write("< " + mTimestamp + " > < " + picture.getTimestamp().format(formatter) + " > < ");
	        writer.write(picture.getImageInfo().getImageId() + "; " + picture.getImageInfo().getImageFileName() + "; ; > < ");

	        if (picture.getStuffs() != null && picture.getStuffs().length > 0) {
	            writer.write("[");
	            for (int i = 0; i < picture.getStuffs().length; i++) {
	                Stuff stuff = picture.getStuffs()[i];
	                writer.write(stuff.getID() + "; " + stuff.getType() + "; " + stuff.getName());
	                if (stuff.getStuffTags() != null && stuff.getStuffTags().length > 0) {
	                    writer.write("; " + String.join(" ", stuff.getStuffTags()));
	                }
	                if (i < picture.getStuffs().length - 1) {
	                    writer.write(" ] [ ");
	                }
	            }
	            writer.write(" ]");
	        }

	        writer.write(" > <");

	        if (picture.getTags() != null && picture.getTags().length > 0) {
	            writer.write(String.join(" ", picture.getTags()));
	        }
	        writer.write(" > <");

	        if (picture.getComment() != null && !picture.getComment().isEmpty()) {
	            writer.write(picture.getComment());
	        }

	        writer.write(" > ");
	        writer.newLine();
	    }

	 
	 
	
}