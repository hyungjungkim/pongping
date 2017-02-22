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
	// Need Default Path
	
	private static String defaultPath = "C:\\Users\\Administrator\\Desktop\\fileServer";
	
	private Socket sock;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private FileInputStream fis = null;
	private FileOutputStream fos = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream out = null;
	//
	RequestInfo rqInfo = null;

	public FileClientLogic(Socket sock) {
		// sock = tcpRl.getClient();
		this.sock = sock;
	}

	// FileUpload Method using OutputStream to Server
	@Override
	public List<DirFile> FileUpload(String userId, String localPath, String serverPath)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		// init RequestInfo Object
		// ObjectOutputStream, Object Serializable
		rqInfo = new RequestInfo(new FileInfo(userId, serverPath, ""), userId, ServiceNum.UPLOAD);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
		fis = new FileInputStream(localPath);
		ListInfor retList = null;
		// need localPath parsing for file name
		// String fileName = localPath.substring(localPath.lastIndexOf("/"),
		// localPath.length());

		byte[] contentBytes = new byte[1024];

		try {
			out.writeObject(rqInfo);
			while (true) {
				int count = fis.read(contentBytes);

				if (count == -1) {
					break;
				}

				dos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			out.close();
			fis.close();
			dos.close();
		}
		// TODO
		// DeSerializable
		try {
			retList = (ListInfor) ois.readObject();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			ois.close();
		}
		return retList.getListInfor();
	}

	// FileDownload Method using InputStream from Server
	@Override
	public boolean FileDownload(String userId, String localPath) throws IOException {
		// TODO Auto-generated method stub
		rqInfo = new RequestInfo(new FileInfo(userId, localPath, ""), userId, ServiceNum.DOWNLOAD);
		out = new ObjectOutputStream(sock.getOutputStream());
		out.writeObject(rqInfo);
		byte[] contentBytes = new byte[1024];
		try {
			dis = new DataInputStream(sock.getInputStream());
			// defaultPath + fileName
			fos = new FileOutputStream(defaultPath+localPath.substring(localPath.lastIndexOf("/"), localPath.length()));
			while (true) {
				int count = dis.read(contentBytes);

				if (count == -1) {
					break;
				}

				fos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {

			e.getStackTrace();
		}finally{
			out.close();
			dis.close();
			fos.close();
		}
		return true;
	}

	// FileRemove Method
	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ListInfor retList = null;
		rqInfo = new RequestInfo(new FileInfo(userId, currentPath, ""), userId, ServiceNum.RMFILE);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}

		return retList.getListInfor();
	}

	// ChangeName
	@Override
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// need parsing
		ListInfor retList = null;
		// parsing path , need to separate File/Directory
		String fileName = newPath.substring(newPath.lastIndexOf("/"), newPath.length());
		if (fileName.lastIndexOf(".") == -1)
			rqInfo = new RequestInfo(new FileInfo(userId, currentPath, newPath), userId, ServiceNum.CNGDIRNAME);
		else
			rqInfo = new RequestInfo(new FileInfo(userId, currentPath, newPath), userId, ServiceNum.CNGFILENAME);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());

		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}

		return retList.getListInfor();
	}

	// FileSearch
	@Override
	public List<DirFile> FileSearch(String userId, String searchName) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// path + fileName;
		ListInfor retList = null;
		rqInfo = new RequestInfo(new FileInfo(userId, searchName, ""), userId, ServiceNum.SEARCH);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}
		return retList.getListInfor();
	}

	// DirectoryCreate
	@Override
	public List<DirFile> DirectoryCreate(String userId, String currentPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ListInfor retList = null;
		rqInfo = new RequestInfo(new FileInfo(userId, currentPath, ""), userId, ServiceNum.MKDIR);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}

		return retList.getListInfor();
	}

	// DirectoryRemove
	@Override
	public List<DirFile> DirectoryRemove(String userId, String currentPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ListInfor retList = null;
		rqInfo = new RequestInfo(new FileInfo(userId, currentPath, ""), userId, ServiceNum.RMVDIR);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}
		return retList.getListInfor();
	}

	// ShowList
	@Override
	public List<DirFile> ShowList(String userId, String currentPath) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// first page & back page function
		ListInfor retList = null;
		rqInfo = new RequestInfo(new FileInfo(userId, currentPath, ""), userId, ServiceNum.SHOWLIST);
		out = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		try {
			out.writeObject(rqInfo);
			retList = (ListInfor) ois.readObject();
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			out.close();
			ois.close();
		}

		return retList.getListInfor();
	}

}
