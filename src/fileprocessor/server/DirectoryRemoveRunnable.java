package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;

public class DirectoryRemoveRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	public DirectoryRemoveRunnable(FileInfo fileInfo, Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.DirectoryRemove(this.fileInfo);
	}
	public List<DirFile> DirectoryRemove(FileInfo fileInfor) {
		// TODO client requesting path => to DB
		String serverPath = null; // from DB
		return null;
	}
}
