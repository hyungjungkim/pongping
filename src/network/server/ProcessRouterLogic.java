package network.server;

import java.net.Socket;

import db.domain.FileInfo;
import db.domain.RequestInfo;
import db.domain.ResponseInfo;

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
		this.userId=requestInfo.userId;
		this.serviceNum=requestInfo.serviceNum;
		this.fileInfo=requestInfo.fileInfo;
	}

	/***
	 * 
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			
			//RequestInfo requestInfo =(RequestInfo) read();
			this.depacketizer();
			if(this.serviceNum == ServiceNum.UPLOAD);
			//하면 upload함수 호출
		}
		
	}

}
