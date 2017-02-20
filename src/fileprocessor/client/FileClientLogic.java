package fileprocessor.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class FileClientLogic implements FileClient {
	//
	private Socket sock;

	public FileClientLogic(Socket sock) {
		//sock = tcpRl.getClient();
		
		this.sock = sock;
	}

	@Override
<<<<<<< HEAD
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
=======
	public List<Object> FileUpload(String userId, String localPath) {
		// TODO Auto-generated method stub
>>>>>>> e136e109014c30b6693426bf663d33ce4d4a032a
		return null;
	}

	@Override
	public boolean FileDownload(String userId, String localPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> FileRemove(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> FileSearch(String userId, String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeName(String userId, String currentPath){
		return false;
	}

	@Override
	public List<Object> DirectoryCreate(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> DirectoryRemove(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
<<<<<<< HEAD
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) {
=======
	public List<Object> ShowList(String userId, String currentPath) {
>>>>>>> e136e109014c30b6693426bf663d33ce4d4a032a
		// TODO Auto-generated method stub
		return null;
	}

}
