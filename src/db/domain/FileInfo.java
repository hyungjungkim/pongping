package db.domain;

import java.io.Serializable;

/**
 * @author 占쏙옙占승븝옙, 占쏙옙占쌍쇽옙
 * 占쏙옙占쏙옙 占쏙옙占� 占쏙옙 占쏙옙占쏙옙占� id, 占쏙옙占쏙옙 占쏙옙호占쏙옙 占쏙옙占쏙옙占쏙옙 占쌍댐옙 클占쏙옙占쏙옙
 *    ___________________________          
 *    |FileInfo    |            |
 *    |   占쏙옙占싹곤옙占�     | 占쏙옙占쏙옙占폠D    |
 *    |            |            |
 *    |____________|____________|
 *
 */

public class FileInfo implements Serializable{
	
	private String userId;
	private String currentPath;
	private String newPath;
		
	public FileInfo(String userId, String currentPath, String newPath) {
		this.userId = userId;
		this.currentPath = currentPath;
		this.newPath = newPath;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
