package fileprocessor.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;

public class FileServerLogic implements FileSever {
	private Socket sock;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	FileInputStream fis = null;
	FileOutputStream fos = null;
	
	public FileServerLogic(Socket sock) {
		// TODO Auto-generated constructor stub
		this.sock = sock;
	}
	
	@Override
	public List<DirFile> FileUpload(FileInfo fileInfor) {
		// TODO Auto-generated method stub
		byte[] contentBytes = new byte[1024];

		try {
			dis = new DataInputStream(sock.getInputStream());
			fos = new FileOutputStream(fileInfor.getFilePath());
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
		return null;
	}

	@Override
	public boolean FileDownload(FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) {
		// TODO Auto-generated method stub
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
		return null;
	}


}
