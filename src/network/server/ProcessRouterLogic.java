package network.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.net.Socket;

import db.domain.FileInfo;
import db.domain.RequestInfo;

public class ProcessRouterLogic extends Thread implements ProcessRouter {
	
	private String userId;
	private ServiceNum serviceNum;
	private FileInfo fileInfo;
	private Socket sock;
	
	public ProcessRouterLogic(Socket sock){
		
		this.sock =sock;
		
	}
	
	/***
	 * 요청번호를 받아서 ServiceNum(열거형)을 리턴함
	 */
	@Override
	public void depacketizer(RequestInfo requestInfo) {
		// TODO Auto-generated method stub
		this.userId=requestInfo.getUserId();
		this.serviceNum=requestInfo.getServiceNum();
		this.fileInfo=requestInfo.getFileInfo();
	}

	/***
	 * 
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			
			
			try {
				FileInputStream fis = new FileInputStream("objectfile.ser");
				ObjectInputStream in = new ObjectInputStream(fis);
				RequestInfo requestInfo = (RequestInfo)in.readObject();
				this.depacketizer(requestInfo);
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			//RequestInfo requestInfo =(RequestInfo) read();
			
			if(this.serviceNum == ServiceNum.UPLOAD);
			//하면 upload함수 호출
		}
		
	}

}
