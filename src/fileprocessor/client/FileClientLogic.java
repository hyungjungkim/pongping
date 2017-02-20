package fileprocessor.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;

public class FileClientLogic implements FileClient {
	//
	private Socket sock;

	public FileClientLogic(Socket sock) {
		//sock = tcpRl.getClient();
		
		this.sock = sock;
	}

	@Override
	public List<DirFile> FileUpload(String userId, String localPath) throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		//localPath parsing ÇØ¾ßÇÔ.
		FileInputStream fis = new FileInputStream(localPath);

		byte[] contentBytes = new byte[1024];
		
		try {
			dos = new DataOutputStream(sock.getOutputStream());
			while (true) {
				int count = fis.read(contentBytes);

				if (count == -1){
					break;
				}

				dos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean FileDownload(String userId, String localPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DirFile> FileRemove(String userId, String currentPath) {
		// TODO Auto-generated method stub
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
