package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;

public interface FileSever {
	//
	/*
	 * 
	 * File Method
	 */
	public List<DirFile> FileUpload(Socket socket, FileInfo fileInfor);
	public boolean FileDownload(Socket socket, FileInfo fileInfor);
	public List<DirFile> FileRemove(String userId, String currentPath);
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath);
	public List<DirFile> FileSearch(String userId, String searchName);
	/*
	 *  Directory Method
	 */
	public List<DirFile> DirectoryCreate(Socket socket, FileInfo fileInfor);
	public List<DirFile> DirectoryRemove(Socket socket, FileInfo fileInfor);
	public List<DirFile> ShowList(String userId, String currentPath);
}
