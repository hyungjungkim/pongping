package fileprocessor.client;

import java.io.IOException;
import java.util.List;

public interface FileClient {
	//
	/*
	 *  userID => Default Variable
	 *  localPath => File, Directory 업로드, 다운, 생성할 Local 경로
	 *  currentPath => 현재 갖고있는 경로
	 *  File Method
	 */
<<<<<<< HEAD
	public List<DirFile> FileUpload(String userId, String localPath) throws IOException;
	public boolean FileDownload(String userId, String localPath);
	public List<DirFile> FileRemove(String userId, String currentPath);
	public List<DirFile> ChangeName(String userId, String currentPath, String newPath);
	public List<DirFile> FileSearch(String userId, String searchName);
=======
	public List<Object> FileUpload(String userId, String localPath);
	public boolean FileDownload(String userId, String localPath);
	public List<Object> FileRemove(String userId, String currentPath);
	public boolean changeName(String userId, String currentPath);
	public List<Object> FileSearch(String userId, String searchName);
>>>>>>> e136e109014c30b6693426bf663d33ce4d4a032a
	/*
	 *  Directory Method
	 */
	public List<Object> DirectoryCreate(String userId, String currentPath);
	public List<Object> DirectoryRemove(String userId, String currentPath);
	public List<Object> ShowList(String userId, String currentPath);
	
	
}
