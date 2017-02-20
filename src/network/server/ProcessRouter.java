package network.server;

import db.domain.ResponseInfo;

public interface ProcessRouter {
	
	public ServiceNum depacketizer();
	public ResponseInfo packetizer();
	public void runService(ServiceNum serviceNum);
		
}
