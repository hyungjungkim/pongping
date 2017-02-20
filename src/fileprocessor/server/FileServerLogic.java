package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.FileInfo;

public class FileServerLogic implements FileSever {

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
	public List<Object> ShowList(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> FileUpload(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean FileDownload(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> DirectoryCreate(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> DirectoryRemove(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

}
