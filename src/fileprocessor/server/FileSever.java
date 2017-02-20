package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.FileInfo;

public interface FileSever {
	//
	/*
	 * 
	 * File Method
	 */
	public List<Object> FileUpload(Socket socket, FileInfo fileInfor);
	public boolean FileDownload(Socket socket, FileInfo fileInfor);
	public List<Object> FileRemove(String userId, String currentPath);
	public List<Object> FileSearch(String userId, String searchName);
	/*
	 *  Directory Method
	 */
	public List<Object> DirectoryCreate(Socket socket, FileInfo fileInfor);
	public List<Object> DirectoryRemove(Socket socket, FileInfo fileInfor);
	public List<Object> ShowList(String userId, String currentPath);
}
