import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class multiMediaGUIClient  extends JFrame implements ActionListener,Runnable{
	
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
	JPanel panel1;
	JPanel drawPanel;
	JPanel totPanel;
	JPanel panel2;
	JPanel audiencePanel;
	
	static Thread t;
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	String name=mainGUI.getClientName();
	String ip=mainGUI.getIp();
	int port=mainGUI.getPort();
	String message;
	
	public multiMediaGUIClient() {
		// TODO Auto-generated constructor stub
		frame=new JFrame("Online Multimedia Table-Client");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize-100,ySize-100);
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
		drawPanel.add(new JLabel("draw compenent"));
		drawPanel.setPreferredSize(new Dimension(1100, 450));
	    drawPanel.setBackground(Color.BLUE);
		
	    totPanel=new JPanel();
		totPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Presentation"));
		totPanel.setPreferredSize(new Dimension(1200, 550));
		totPanel.add(panel1);
		totPanel.setBackground(Color.CYAN);
		totPanel.add(drawPanel);
		centerPanel.add(totPanel);
		
		panel2 = new JPanel();
		LayoutManager lm=new BoxLayout(panel2,BoxLayout.Y_AXIS);
		panel2.setLayout(lm);

	    panel2.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"CHAT"));
	    panel2.setPreferredSize(null);
	    chatareaT.setEditable(false);
	    panel2.add(chatareaT);
	    centerPanel.add(panel2);
	    JScrollPane scroll=new JScrollPane(panel2);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1200,300));
	    centerPanel.add(scroll);
	    
		audiencePanel=new JPanel();
		audiencePanel.setBorder(new TitledBorder(new LineBorder(Color.black),"AUDIENCE"));
		
		audiencePanel.setPreferredSize(new Dimension(200, 850));
		eastPanel.add(audiencePanel);
		
		frame.setLayout(new BorderLayout());
		frame.add("Center", centerPanel);
		frame.add("South", southPanel);
		frame.add("West", westPanel);
		frame.add("East", eastPanel);
		frame.add("North", northPanel);
		sendB.addActionListener(this);

		frame.setVisible(true);

		
	}
	
	public static void main(String[] args) {
		multiMediaGUIClient clientgui= new multiMediaGUIClient();
		t=new Thread(clientgui);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			socket=new Socket(ip,port);
			System.out.println("connected to :"+ip+"and client name :"+name);
			output=new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input=new ObjectInputStream(socket.getInputStream());
			
			whileChatting();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	private void whileChatting() throws IOException {
		// TODO Auto-generated method stub
		while (!"END".equals(message)) {
			try {
				message=(String) input.readObject();
				chatareaT.append("\n"+message);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==sendB) {
			message=chatT.getText();
			sendMessage(message);
			chatT.setText("");
		}
	}

	private void sendMessage(String message) {
		// TODO Auto-generated method stub
		try {
			output.writeObject("\n"+name+" : "+message);
			output.flush();
			chatareaT.append("\n"+name+" : "+message);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
