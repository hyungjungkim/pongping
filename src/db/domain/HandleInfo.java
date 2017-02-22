package db.domain;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleInfo {
	
	private Socket sock;
	private FileInfo fileInfo;
	private ObjectInputStream in;	
	private ObjectOutputStream out;	
	public HandleInfo(Socket sock, FileInfo fileInfo, ObjectInputStream in, ObjectOutputStream out) {
		this.out = out;
		this.sock = sock;
		this.fileInfo = fileInfo;
		this.in = in;
		
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

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	
	
}
