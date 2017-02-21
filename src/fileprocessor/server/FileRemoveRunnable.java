package fileprocessor.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;

public class FileRemoveRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	
	
	public FileRemoveRunnable(FileInfo fileInfo, Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.FileRemove(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException {
		// TODO client requesting path => to DB
		String fileRemovePath = null; // from DB
		out = new ObjectOutputStream(sock.getOutputStream());
		File file = new File(fileRemovePath);
		if(file.delete()){
			System.out.println(file.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}
		// TODO current list of current depth (from DB)
		// Serializable
		ListInfor retList = new ListInfor();
		// TODO retList.setListInfor(list);
		out.writeObject(retList);
		return null;
	}
}
