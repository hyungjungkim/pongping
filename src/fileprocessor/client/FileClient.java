package fileprocessor.client;

import java.io.IOException;
import java.util.List;

import db.domain.DirFile;

public interface FileClient {
	//
	/*
	 *  userID => Default Variable
	 *  localPath => File, Directory 업로드, 다운, 생성할 Local 경로
	 *  currentPath => 현재 갖고있는 경로
	 *  File Method
	 */

	public List<DirFile> FileUpload(String userId, String localPath) throws IOException;
	public boolean FileDownload(String userId, String localPath);
	public List<DirFile> FileRemove(String userId, String currentPath);
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath);
	public List<DirFile> FileSearch(String userId, String searchName);

	/*
	 *  Directory Method
	 */
	public List<DirFile> DirectoryCreate(String userId, String currentPath);
	public List<DirFile> DirectoryRemove(String userId, String currentPath);
	public List<DirFile> ShowList(String userId, String currentPath);
	
	
}
