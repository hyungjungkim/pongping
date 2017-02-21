package fileprocessor.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;

public class FileUploadRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream ois = null;
	private DataInputStream dis = null;
	private FileOutputStream fos = null;
	
	public FileUploadRunnable(FileInfo fileInfo , Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.FileUpload(this.fileInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<DirFile> FileUpload(FileInfo fileInfor) throws IOException {
		// TODO client requesting path => to DB
		String serverSavePath = null; // from DB
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream()); 
		byte[] contentBytes = new byte[1024];

		try {
			dis = new DataInputStream(sock.getInputStream());
			fos = new FileOutputStream(serverSavePath);
			while (true) {
				int count = dis.read(contentBytes);
				if (count == -1) {
					break;
				}
				fos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		// TODO current list of current depth (from DB)
		// Serializable
		ListInfor retList = new ListInfor();
		// TODO retList.setListInfor(list);
		out.writeObject(retList);
		return null;
	}
}
