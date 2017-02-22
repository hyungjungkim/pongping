package db.domain;

import java.net.Socket;

public class HandleInfo {
	
	private Socket sock;
	private FileInfo fileInfo;
		
	public HandleInfo(Socket sock, FileInfo fileInfo) {
		
		this.sock = sock;
		this.fileInfo = fileInfo;
		
	}

	public Socket getSock() {
		return sock;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	
	
}
