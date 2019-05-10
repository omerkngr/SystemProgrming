import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.ImageView;

public class MultimediaEducationGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JTextField chatT=new JTextField(30);
	
	JButton sendB=new JButton("SEND");
	JButton chooseB=new JButton("CHOOSE FOLDER");
	JButton previousB=new JButton("<PREVIOUS");
	JButton nextB=new JButton("NEXT>");
	JButton shareB=new JButton("SHARE");
	
	Label label=new Label("chat");
	JPanel panel1;
	JPanel drawPanel;
	JPanel totPanel;
	JPanel panel2;
	JPanel audiencePanel;
	
	public MultimediaEducationGUI() {
		// TODO Auto-generated constructor stub
		frame=new JFrame("Online Multimedia Table");
		frame.setSize(700, 700);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		chatT.setText("enter message");
		southPanel.add(chatT);
		southPanel.add(sendB);
		
		panel1=new JPanel();
		panel1.add(chooseB);
		panel1.add(previousB);
		panel1.add(nextB);
		panel1.add(shareB);
		
		drawPanel=new JPanel();
		drawPanel.add(new JLabel("draw compenent"));
		drawPanel.setPreferredSize(new Dimension(350, 250));
	    //drawPanel.setBackground(Color.BLUE);
		
	    totPanel=new JPanel();
		totPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Presentation"));
		totPanel.setPreferredSize(new Dimension(450, 400));
		totPanel.add(panel1);
		totPanel.add(drawPanel);
		centerPanel.add(totPanel);
		
		panel2 = new JPanel();
	    panel2.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"CHAT"));
	    panel2.add(new JLabel("LABEL"));
	    panel2.setPreferredSize(new Dimension(450, 100));
	  //panel2.setBackground(Color.BLACK);
	    centerPanel.add(panel2);
		
		audiencePanel=new JPanel();
		audiencePanel.setBorder(new TitledBorder(new LineBorder(Color.black),"AUDIENCE"));
		audiencePanel.add(new Label("ins"));
		audiencePanel.setPreferredSize(new Dimension(125, 450));
		eastPanel.add(audiencePanel);
		
		frame.setLayout(new BorderLayout());
		frame.add("Center", centerPanel);
		frame.add("South", southPanel);
		frame.add("West", westPanel);
		frame.add("East", eastPanel);
		frame.add("North", northPanel);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==sendB) {
			
		}
	}
	public String getChatString() {
		return chatT.getText();
	}

}
