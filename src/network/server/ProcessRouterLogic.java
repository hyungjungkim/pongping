package network.server;

import java.net.Socket;

import db.domain.FileInfo;
import db.domain.ResponseInfo;

public class ProcessRouterLogic extends Thread implements ProcessRouter {
	
	private String userId;
	private ServiceNum serviceNum;
	private FileInfo fileInfo;
	private Socket sock;
	
	public ProcessRouterLogic (Socket sock){
		this.sock =sock;
		
	}
	
	/***
	 * 요청번호를 받아서 ServiceNum(열거형)을 리턴함
	 */
	@Override
	public void depacketizer() {
		// TODO Auto-generated method stub
		this.userId=requestInfo.userId;
		this.serviceNum=requestInfo.serviceNum;
		this.fileInfo=requestInfo.fileInfo;
	}

	/***
	 * 
	 */
	@Override
	public void runService(ServiceNum serviceNum) {
		// TODO Auto-generated method stub
		while(true){
			
			//RequestInfo requestInfo =(RequestInfo) read();
			this.depacketizer();
			if(this.serviceNum == ServiceNum.UPLOAD);
			//하면 upload함수 호출
		}
	}
	
	
	/***
	 * FileInfo객체를 속성으로 갖고있는 ResponseInfo를 리턴함.
	 * FileInfo에는 UserId와 FilePath갖고 있다.
	 */
	@Override
	public ResponseInfo packetizer() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
