package db.domain;

/**
 * @author 강승보, 방주선
 * 파일 경로 및 사용자 id, 서비스 번호를 가지고 있는 클래스
 *    ___________________________          
 *    |FileInfo    |            |
 *    |   파일경로     | 사용자ID    |
 *    |            |            |
 *    |____________|____________|
 *
 */

public class FileInfo {
	
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
