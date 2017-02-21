package db.domain;

public class PathMapping {
	private String userId;
	private String originFileName;
	private String serverPath;
	private String clientPath;
	
	public PathMapping(){
		
	}
	
	public PathMapping(String userId, String originFileName, String clientPath) {
		super();
		this.userId = userId;
		this.originFileName = originFileName;
		this.clientPath = clientPath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getClientPath() {
		return clientPath;
	}

	public void setClientPath(String clientPath) {
		this.clientPath = clientPath;
	}

	
	public void print() {
		System.out.println("PathMapping [userId=" + userId + ", originFileName=" + originFileName + ", serverPath=" + serverPath
				+ ", clientPath=" + clientPath + ", getUserId()=" + getUserId() + ", getOriginFileName()="
				+ getOriginFileName() + ", getServerPath()=" + getServerPath() + ", getClientPath()=" + getClientPath()
				+ "]");
	}
	
	
}
