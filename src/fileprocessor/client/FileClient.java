package fileprocessor.client;

import java.io.IOException;
import java.util.List;

import db.domain.DirFile;

public interface FileClient {
	//
	/*
	 *  userID => Default Variable
	 *  localPath => File, Directory ���ε�, �ٿ�, ������ Local ���
	 *  currentPath => ���� �����ִ� ���
	 *  File Method
	 */

	public List<DirFile> FileUpload(String userId, String localPath , String serverPath) throws IOException, ClassNotFoundException;
	public boolean FileDownload(String userId, String localPath) throws IOException;
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException, ClassNotFoundException;
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) throws IOException, ClassNotFoundException;
	public List<DirFile> FileSearch(String userId, String searchName) throws IOException, ClassNotFoundException;

	/*
	 *  Directory Method
	 */
	public List<DirFile> DirectoryCreate(String userId, String currentPath) throws IOException, ClassNotFoundException;
	public List<DirFile> DirectoryRemove(String userId, String currentPath) throws IOException, ClassNotFoundException;
	public List<DirFile> ShowList(String userId, String currentPath) throws IOException, ClassNotFoundException;
	
	
}
