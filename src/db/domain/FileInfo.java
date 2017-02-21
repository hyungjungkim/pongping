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
	
	private String filePath;
	private String userId;
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
