package fileprocessor.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;

public class FileServerLogic implements FileSever {
	private Socket sock;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	FileInputStream fis = null;
	FileOutputStream fos = null;
	ObjectOutputStream out = null;
	ObjectInputStream ois = null;

	public FileServerLogic(Socket sock) {
		// TODO Auto-generated constructor stub
		this.sock = sock;
	}

	@Override
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

	@Override
	public void FileDownload(FileInfo fileInfor) throws IOException {
		// TODO client requesting path => to DB
		String serverDownPath = null; // from DB
		byte[] contentBytes = new byte[1024];

		try {
			dos = new DataOutputStream(sock.getOutputStream());
			fis = new FileInputStream(serverDownPath);
			while (true) {
				int count = fis.read(contentBytes);
				if (count == -1) {
					break;
				}

				dos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		// TODO
	}

	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException {
		// TODO client requesting path => to DB
		String fileRemovePath = null; // from DB
		ois = new ObjectInputStream(sock.getInputStream()); 
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

	@Override
	public List<DirFile> FileSearch(String userId, String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> DirectoryCreate(FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> DirectoryRemove(FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> ShowList(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) {
		// TODO Auto-generated method stub 
		// 회의 필요
		return null;
	}

}
