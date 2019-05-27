
public class ServerButtonThread extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
			
		try {
			multiMediaGUIServer.main(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		
	}

}
