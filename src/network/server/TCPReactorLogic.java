package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import db.domain.RequestInfo;

public class TCPReactorLogic implements TCPReactor {

	private ServerSocket servSock;
	public TCPReactorLogic() {
		// TODO Auto-generated constructor stub
		try {
			
			this.servSock = new ServerSocket(9999);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void startServer() {
		// TODO Auto-generated method stub
		try{
			
			while(true){
				
				Socket sock = this.servSock.accept();
				ProcessRouterLogic processRouterLogic = new ProcessRouterLogic(sock);
				Thread t = new Thread(processRouterLogic);
				t.start();
				
			}
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		
	}
	
	
}
