package fileprocessor.client;

import java.util.List;

public interface FileClient {
	//
	/*
	 *  userID => Default Variable
	 *  localPath => File, Directory ���ε�, �ٿ�, ������ Local ���
	 *  currentPath => ���� �����ִ� ���
	 *  File Method
	 */
	public List<Object> FileUpload(String userId, String localPath);
	public boolean FileDownload(String userId, String localPath);
	public List<Object> FileRemove(String userId, String currentPath);
	public boolean changeName(String userId, String currentPath);
	public List<Object> FileSearch(String userId, String searchName);
	/*
	 *  Directory Method
	 */
	public List<Object> DirectoryCreate(String userId, String currentPath);
	public List<Object> DirectoryRemove(String userId, String currentPath);
	public List<Object> ShowList(String userId, String currentPath);
	
	
}
