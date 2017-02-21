package fileprocessor.server;

import java.io.IOException;
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
	public List<DirFile> FileUpload(FileInfo fileInfor) throws IOException;
	public void FileDownload(FileInfo fileInfor) throws IOException;
	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException;
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) throws IOException;
	public List<DirFile> FileSearch(String userId, String searchName) throws IOException;
	/*
	 *  Directory Method
	 */
	public List<DirFile> DirectoryCreate(FileInfo fileInfor) throws IOException;
	public List<DirFile> DirectoryRemove(FileInfo fileInfor);
	public List<DirFile> ShowList(String userId, String currentPath) throws IOException;
}
