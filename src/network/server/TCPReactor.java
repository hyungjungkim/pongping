package network.server;

import java.net.Socket;

import db.domain.RequestInfo;

public interface TCPReactor {

	public Socket getClient();
	void respondTo(RequestInfo requestInfo);
	
}
