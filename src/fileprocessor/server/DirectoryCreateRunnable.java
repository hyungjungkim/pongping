package fileprocessor.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;

public class DirectoryCreateRunnable implements Runnable{
	//
	private Socket sock = null;
	private FileInfo fileInfo = null;
	private ObjectOutputStream out = null;
	
	public DirectoryCreateRunnable(FileInfo fileInfo, Socket sock) {
		// TODO Auto-generated constructor stub
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.DirectoryCreate(this.fileInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<DirFile> DirectoryCreate(FileInfo fileInfor) throws IOException {
		// TODO client requesting path => to DB
		String dirCreatePath = null; // from DB
		File file = new File(dirCreatePath);
		if(!file.exists()){
			file.mkdirs();
			System.out.println("Directory Name = " + dirCreatePath); // dedug
		}
		out = new ObjectOutputStream(sock.getOutputStream());
		// Serializable
		ListInfor retList = new ListInfor();
		// TODO retList.setListInfor(list);
		out.writeObject(retList);
		return null;
	}
}
