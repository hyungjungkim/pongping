package db.domain;

/**
 * @author ���º�, ���ּ�
 * ���� ��� �� ����� id, ���� ��ȣ�� ������ �ִ� Ŭ����
 *    ___________________________          
 *    |FileInfo    |            |
 *    |   ���ϰ��     | �����ID    |
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
