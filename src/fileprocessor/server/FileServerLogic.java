package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;

public class FileServerLogic implements FileSever {

	@Override
	public List<DirFile> FileUpload(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean FileDownload(Socket socket, FileInfo fileInfor) {
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
	public List<DirFile> DirectoryCreate(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> DirectoryRemove(Socket socket, FileInfo fileInfor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirFile> ShowList(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}


}
