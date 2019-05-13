import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

		Socket serverClient;
		int clientno;
		String message;
		public ServerThread (Socket socket, int cnt) {
			this.serverClient=socket;
			this.clientno=cnt;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			      DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			      String clientMessage="", serverMessage="";
			      while(!clientMessage.equals("exit")){
			        clientMessage=inStream.readUTF();
			        System.out.println("From Client-" +clientno+ "message is :"+clientMessage);
			      // serverMessage=inStream.readUTF();
			      //  serverMessage="From Server to Client-" + clientno + " message is " + serverMessage + " is " +message;
			     //   outStream.writeUTF(serverMessage);
			        outStream.flush();
			       
			      }
			      inStream.close();
				     outStream.close();
				    serverClient.close();
			    }catch(IOException ex){
			    	ex.printStackTrace();
			      System.out.println(ex);
			    }finally{
			      System.out.println("Client - " + clientno + " exit!! ");
			    }	
			}
	}
