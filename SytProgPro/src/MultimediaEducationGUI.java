import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MultimediaEducationGUI {
	JFrame frame;
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel northPanel=new JPanel();
	
	public MultimediaEducationGUI() {
		// TODO Auto-generated constructor stub
		frame=new JFrame("xxx");
		frame.setSize(560, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eastPanel.add(new Label("Instractor"));
		//centerPanel.setBackground(Color.blue);
		//southPanel.setBackground(Color.BLACK);
		//westPanel.setBackground(Color.cyan);
		//eastPanel.setBackground(Color.GREEN);
		//northPanel.setBackground(Color.orange);
		frame.add("Center", centerPanel);
		frame.add("South", southPanel);
		frame.add("West", westPanel);
		frame.add("East", eastPanel);
		frame.add("North", northPanel);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MultimediaEducationGUI();

	}

}
