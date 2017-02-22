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
			
			this.servSock = new ServerSocket(9900);
			
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
				System.out.println("[Server] " + sock.getInetAddress() + " 접속");
				Thread processRouterThread = new Thread(processRouterLogic);
				System.out.println("[Server] 라우터 스레드 시작");
				processRouterThread.start();
				
			}
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		
	}
	
	
}
