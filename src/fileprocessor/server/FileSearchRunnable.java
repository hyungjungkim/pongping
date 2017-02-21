package fileprocessor.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;

public class FileSearchRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	
	public FileSearchRunnable(FileInfo fileInfo , Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.FileSearch(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<DirFile> FileSearch(String userId, String searchName) throws IOException {
		// TODO client requesting searchName => to DB
		out = new ObjectOutputStream(sock.getOutputStream());
		// Serializable
		ListInfor retList = new ListInfor();
		// TODO retList.setListInfor(list);
		out.writeObject(retList);
		return null;
	}

}
