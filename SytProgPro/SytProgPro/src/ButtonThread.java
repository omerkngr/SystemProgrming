
public class ButtonThread extends Thread{

	int n=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Server.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
