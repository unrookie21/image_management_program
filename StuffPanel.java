import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StuffPanel extends JPanel{ // Stuff 에 대응하는 GUI 클래스
	
	
	private JPanel stuffPane;
	private JTextField typeRight;
	private JTextField nameRight;
	private JTextField tagsRight;
	
	public StuffPanel() // 그냥 Stuff 패널 자체를 생성해야 할때
	{
		
		stuffPane = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		
	
		
		LineBorder paneBorder = new LineBorder(Color.gray);
		stuffPane.setBorder(paneBorder);
		
		// Type 
		JLabel Type = new JLabel("Type");
		gbc.weightx = 0.1; gbc.gridx = 0; gbc.gridy = 0;
		stuffPane.add(Type, gbc);
		
		
	
		// Type 의 TextField
		typeRight = new JTextField();
		LineBorder typeRightBorder = new LineBorder(Color.lightGray);
		typeRight.setBorder(typeRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 0;
		stuffPane.add(typeRight, gbc);
		
		
		
		
		// Name
		JLabel Name = new JLabel("Name");
		gbc.weightx = 0.1;  gbc.gridx = 0; gbc.gridy = 1;
		stuffPane.add(Name, gbc);
		

		
		// Name 의 TextField
		nameRight = new JTextField();
		LineBorder nameRightBorder = new LineBorder(Color.lightGray);
		nameRight.setBorder(nameRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 1;
		stuffPane.add(nameRight, gbc);
		
		
		// Tags
		JLabel Tags = new JLabel("Tags");
		gbc.weightx = 0.1; gbc.gridx = 0; gbc.gridy = 2;
		stuffPane.add(Tags, gbc); 
		
		
		// Tags 의 TextField
		tagsRight = new JTextField();
		LineBorder tagsRightBorder = new LineBorder(Color.lightGray);
		tagsRight.setBorder(tagsRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 2;
		stuffPane.add(tagsRight, gbc);
		
		
		
	}
	
	public StuffPanel(Stuff stuff) // 파일에서 읽어오는 과정에서 PicturePanel 에 stuff 정보를 하나씩 추가할 때
	{
		
		stuffPane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		
		
		LineBorder paneBorder = new LineBorder(Color.gray);
		stuffPane.setBorder(paneBorder);
		
		
		JLabel Type = new JLabel("Type");
		gbc.weightx = 0.1; gbc.gridx = 0; gbc.gridy = 0;
		stuffPane.add(Type, gbc);
		
		
		
			
		JLabel typeRight = new JLabel(stuff.getType());
		LineBorder typeRightBorder = new LineBorder(Color.lightGray);
		typeRight.setBorder(typeRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 0;
		stuffPane.add(typeRight, gbc);
		
		
		// name
	
		JLabel Name = new JLabel("Name");
		gbc.weightx = 0.1; gbc.gridx = 0; gbc.gridy = 1;
		stuffPane.add(Name, gbc);
		
		// nameright
	
		
		JLabel nameRight = new JLabel(stuff.getName());
		LineBorder nameRightBorder = new LineBorder(Color.lightGray);
		nameRight.setBorder(nameRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 1;
		stuffPane.add(nameRight, gbc);
		
		// tag
		
	
		JLabel Tags = new JLabel("Tags");
		gbc.weightx = 0.1; gbc.gridx = 0; gbc.gridy = 2;
		stuffPane.add(Tags, gbc);
		
		// tagright
		
		
		JLabel tagsRight = new JLabel(String.join(" ", stuff.getStuffTags()));
		LineBorder tagsRightBorder = new LineBorder(Color.lightGray);
		tagsRight.setBorder(tagsRightBorder);
		gbc.weightx = 10; gbc.gridx = 1; gbc.gridy = 2;
		stuffPane.add(tagsRight, gbc);
	
	}
	
	public JPanel getstuffPane()
	{
		return stuffPane;
	}
	

	public JTextField getTypeRight() 
	{
		return typeRight;	
	}
	
	public JTextField getNameRight()
	{
		return nameRight;
	}
	
	public JTextField getTagsRight()
	{
		return tagsRight;
	}

}












