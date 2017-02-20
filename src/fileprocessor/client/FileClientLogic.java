package fileprocessor.client;

import java.util.List;

import db.domain.DirFile;

public class FileClientLogic implements FileClient {

	@Override
	public List<DirFile> FileUpload(String userId, String localPath) {
		// TODO Auto-generated method stub
		
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

	@Override
	public boolean ChangeName(String userId, String currentPath, String newPath) {
		// TODO Auto-generated method stub
		return false;
	}

}
