package db.domain;

import java.net.Socket;

public class HandleInfo {
	
	private Socket sock;
	private FileInfo fileInfo;
		
	public HandleInfo(Socket sock, FileInfo fileInfo) {
		
		this.sock = sock;
		this.fileInfo = fileInfo;
		
	}
	
}
