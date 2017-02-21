package fileprocessor.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
import db.domain.RequestInfo;
import network.server.ServiceNum;

public class FileClientLogic implements FileClient {
	//
	private Socket sock;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	FileInputStream fis = null;
	FileOutputStream fos = null;
	
	//
	RequestInfo rqInfo = null;

	public FileClientLogic(Socket sock) {
		// sock = tcpRl.getClient();
		this.sock = sock;
	}

	// FileUpload Method using OutputStream to Server
	@Override
	public List<DirFile> FileUpload(String userId, String localPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		//init RequestInfo Object 
		//ObjectOutputStream, Object Serializable
		rqInfo = new RequestInfo(new FileInfo(userId,localPath,""), userId, ServiceNum.UPLOAD);
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream()); 
		out.writeObject(rqInfo);
		// need localPath parsing for file name
		//String fileName = localPath.substring(localPath.lastIndexOf("/"), localPath.length());
		
		byte[] contentBytes = new byte[1024];

		try {
			dos = new DataOutputStream(sock.getOutputStream());
			fis = new FileInputStream(localPath);
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
		// DeSerializable
		ListInfor retList = (ListInfor)ois.readObject();
		return retList.getListInfor();
	}

	// FileDownload Method using InputStream from Server
	@Override
	public boolean FileDownload(String userId, String localPath) throws IOException {
		// TODO Auto-generated method stub

		byte[] contentBytes = new byte[1024];
		try {
			dis = new DataInputStream(sock.getInputStream());
			fos = new FileOutputStream(localPath);
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
		return true;
	}

	// FileRemove Method
	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		try {
			dos = new DataOutputStream(sock.getOutputStream());
			dos.writeUTF(currentPath);
		} catch (IOException e) {
			e.getStackTrace();
		}
		
		// DeSerializable
		ListInfor retList = (ListInfor)ois.readObject();
		
		return retList.getListInfor();
	}

	@Override
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		rqInfo = new RequestInfo(new FileInfo(userId, currentPath, newPath), userId, ServiceNum.UPLOAD);
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream()); 
		out.writeObject(rqInfo);
		
		ListInfor retList = (ListInfor)ois.readObject();
		
		return retList.getListInfor();
	}

	@Override
	public List<DirFile> FileSearch(String userId, String searchName) {
		// TODO Auto-generated method stub
		// path + fileName;
		return null;
	}

	@Override
	public List<DirFile> DirectoryCreate(String userId, String currentPath) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<DirFile> DirectoryRemove(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> ShowList(String userId, String currentPath) {
		// TODO Auto-generated method stub
		// first page & back page function
		return null;
	}

}
