import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

public class multiMediaGUIServer extends JFrame implements ActionListener,Runnable {
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
	
	static JTextField chatT=new JTextField(30);
	JTextArea chatareaT=new JTextArea(10, 30);
	
	JButton sendB=new JButton("SEND");
	JButton chooseB=new JButton("CHOOSE FOLDER");
	JButton previousB=new JButton("<PREVIOUS");
	JButton nextB=new JButton("NEXT>");
	JButton shareB=new JButton("SHARE");
	Label label=new Label("chat");
	JLabel label2=new JLabel();
	JPanel panel1;
	JPanel drawPanel;
	JPanel totPanel;
	JPanel panel2;
	JPanel audiencePanel;
	
	static Thread t;
	Socket socket;
	ServerSocket serversocket;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	int port=mainGUI.getPort();
	String message;
	String name="instractor";
	
	public multiMediaGUIServer() {
		// TODO Auto-generated constructor stub
		frame=new JFrame("Online Multimedia-Server");
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		label2.setBounds(10, 10, 850, 400);
		DrawPanel panel=new DrawPanel();
		panel.setPreferredSize(new Dimension(850, 400));
		drawPanel.add(panel);
		drawPanel.setPreferredSize(new Dimension(850, 400));
		
	    totPanel=new JPanel();
		totPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Presentation"));
		totPanel.setPreferredSize(new Dimension(950, 500));
		totPanel.add(panel1);
		
		totPanel.add(drawPanel);
		centerPanel.add(totPanel);
		
		panel2 = new JPanel();
	    panel2.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"CHAT"));
	    panel2.setPreferredSize(null);
	    chatareaT.setEditable(false);
	    panel2.add(chatareaT);
	    centerPanel.add(panel2);
	    JScrollPane scroll=new JScrollPane(panel2);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(800,200));
	    centerPanel.add(scroll);
	    
		audiencePanel=new JPanel();
		audiencePanel.setBorder(new TitledBorder(new LineBorder(Color.black),"AUDIENCE"));	
		audiencePanel.setPreferredSize(new Dimension(100, 600));
		eastPanel.add(audiencePanel);
		
		frame.setLayout(new BorderLayout());
		frame.add("Center", centerPanel);
		frame.add("South", southPanel);
		frame.add("West", westPanel);
		frame.add("East", eastPanel);
		frame.add("North", northPanel);
		sendB.addActionListener(this);
		chooseB.addActionListener(this);

		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		multiMediaGUIServer servergui=new multiMediaGUIServer();
		t=new Thread(servergui);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serversocket=new ServerSocket(port);
			while (true) {
				System.out.println("waiting for someone to connect");
				socket=serversocket.accept();
				
				output=new ObjectOutputStream(socket.getOutputStream());
				output.flush();
				input=new ObjectInputStream(socket.getInputStream());
				
				whileChatting();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}

	private void whileChatting() {
		// TODO Auto-generated method stub
		String message="";
		
		while (!message.equals("END")) {
			try {
				message=(String) input.readObject();
				chatareaT.append("\n"+message);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==sendB) {
			sendMessage(chatT.getText());
			chatT.setText("");
		}
		
		if(e.getSource()==chooseB) {
			JFileChooser chooser=new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter=new FileNameExtensionFilter(".Images","jpg","jpeg","gif","png");
			chooser.addChoosableFileFilter(filter);
			int returnValue=chooser.showOpenDialog(frame);
			
			if(returnValue==JFileChooser.APPROVE_OPTION) {
				File selectedFile=chooser.getSelectedFile();
				String path=selectedFile.getAbsolutePath();
				label2.setIcon(resizeImage(path));
				drawPanel.add(label2);
			}
		}
	}

	public ImageIcon resizeImage(String path) {
		// TODO Auto-generated method stub
		ImageIcon myimage=new ImageIcon(path);
		Image img=myimage.getImage();
		Image newimg=img.getScaledInstance(label2.getWidth()+850
				,label2.getHeight()+400, Image.SCALE_DEFAULT);
		ImageIcon image=new ImageIcon(newimg);
		return image;
	}

	private void sendMessage(String message) {
		// TODO Auto-generated method stub
		try {
			output.writeObject(name+" : "+message);
			output.flush();
			chatareaT.append("\n"+name+" : "+message);
		} catch (Exception e) {
			// TODO: handle exception
			chatareaT.append("\n Unable to Send Message");
			e.printStackTrace();
		}
	}
}
