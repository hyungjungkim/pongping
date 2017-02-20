package network.server;

import db.domain.ResponseInfo;

public class ProcessRouterLogic implements ProcessRouter {
	
	
	/***
	 * 요청번호를 받아서 ServiceNum(열거형)을 리턴함
	 */
	@Override
	public ServiceNum depacketizer() {
		// TODO Auto-generated method stub
		return null;
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

	/***
	 * 
	 */
	@Override
	public void runService(ServiceNum serviceNum) {
		// TODO Auto-generated method stub
		
	}

	
	
}
