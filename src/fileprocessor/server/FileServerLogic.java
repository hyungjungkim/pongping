package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;

public class FileServerLogic implements FileSever {
	private Socket sock;
	
	public FileServerLogic(Socket sock) {
		// TODO Auto-generated constructor stub
		this.sock = sock;
	}
	
	@Override
	public List<DirFile> FileUpload(FileInfo fileInfor) {
		// TODO Auto-generated method stub
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


}
