import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mainGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton serverStartB=new JButton("Start Server");
	JButton connectB=new JButton("Connect");
	public static JTextField usernameT=new JTextField(15);
	public static JTextField ipT=new JTextField(15);
	public static JTextField portT=new JTextField(15);
	JPanel centerPanel=new JPanel();
	JFrame frame;
	
	String serverperseon="instr";
	public static String clientN;
	static int port;
	static String ip;
	
	public mainGUI() {
		frame=new JFrame("Online Multimedia Education");
		frame.setSize(250, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		
		centerPanel.add(serverStartB);
		centerPanel.add(new JLabel("OR"));
		usernameT.setText("user name");
		centerPanel.add(usernameT);
		ipT.setText("IP address");
		centerPanel.add(ipT);
		portT.setText("Port");
		centerPanel.add(portT);
		centerPanel.add(connectB);
		frame.setLayout(new BorderLayout());
		frame.add("Center", centerPanel);
		serverStartB.addActionListener(this);
		connectB.addActionListener(this);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainGUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			ServerButtonThread sbt=new ServerButtonThread();
			ConnectButtonThread cbt=new ConnectButtonThread();
			
			if (e.getSource()==serverStartB) {
				sbt.start();
			}
				if (e.getSource()==connectB) {
					cbt.start();
				}
	}
	
	public static String getClientName() {
		clientN=usernameT.getText();
		return clientN;
	}
	public static int getPort() {
		port=Integer.parseInt(portT.getText());
		return port;
	}
	public static String getIp() {
		ip=ipT.getText();
		return ip;
	}

}
