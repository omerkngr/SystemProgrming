
public class ConnectButtonThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			multiMediaGUIClient.main(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
