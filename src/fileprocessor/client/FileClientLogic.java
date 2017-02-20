package fileprocessor.client;

import java.util.List;

public class FileClientLogic implements FileClient {

	@Override
	public List<Object> FileUpload(String userId, String localPath) {
		// TODO Auto-generated method stub
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
	public List<Object> ShowList(String userId, String currentPath) {
		// TODO Auto-generated method stub
		return null;
	}

}
