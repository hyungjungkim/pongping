package network.server;

import java.net.Socket;

import db.domain.FileInfo;
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
	 * ��û��ȣ�� �޾Ƽ� ServiceNum(������)�� ������
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
	
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			
			//RequestInfo requestInfo =(RequestInfo) read();
			this.depacketizer();
			if(this.serviceNum == ServiceNum.UPLOAD);
			//�ϸ� upload�Լ� ȣ��
		}
		
	}

	/***
	 * FileInfo��ü�� �Ӽ����� �����ִ� ResponseInfo�� ������.
	 * FileInfo���� UserId�� FilePath���� �ִ�.
	 */
	@Override
	public ResponseInfo packetizer() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
