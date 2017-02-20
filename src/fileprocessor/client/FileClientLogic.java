package fileprocessor.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;

public class FileClientLogic implements FileClient {
	//
	private Socket sock;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	FileInputStream fis = null;
	FileOutputStream fos = null;

	public FileClientLogic(Socket sock) {
		// sock = tcpRl.getClient();

		this.sock = sock;
	}

	// FileUpload Method using OutputStream to Server
	@Override
	public List<DirFile> FileUpload(String userId, String localPath) throws IOException {
		// TODO Auto-generated method stub

		// need localPath parsing for file name
		String fileName = localPath.substring(localPath.lastIndexOf("/"), localPath.length());

		byte[] contentBytes = new byte[1024];

		try {
			dos = new DataOutputStream(sock.getOutputStream());
			fis = new FileInputStream(fileName);
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
		return null;
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
		return false;
	}

	// FileRemove Method
	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException {
		// TODO Auto-generated method stub
		try {
			dos = new DataOutputStream(sock.getOutputStream());
			dos.writeUTF(currentPath);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> FileSearch(String userId, String searchName) {
		// TODO Auto-generated method stub
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
		return null;
	}

}
