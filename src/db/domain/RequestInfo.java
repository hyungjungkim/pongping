package db.domain;

import java.io.Serializable;

import network.server.ServiceNum;

/**
 * @author 占쏙옙占승븝옙, 占쏙옙占쌍쇽옙
 * 占쏙옙占쏙옙 占쏙옙占� 占쏙옙 占쏙옙占쏙옙占� id, 占쏙옙占쏙옙 占쏙옙호占쏙옙 占쏙옙占쏙옙占쏙옙 占쌍댐옙 클占쏙옙占쏙옙
 * ______________________________________________________
 * |  ___________________________         RequestInfo   |
 * |  |FileInfo    |            |                       |
 * |  |   占쏙옙占싹곤옙占�     | 占쏙옙占쏙옙占폠D    |   占쏙옙占쏙옙占폠D, 占쏙옙占쏟스뱄옙호      |
 * |  |            |            |                       |
 * |  |____________|____________|                       |
 * |____________________________________________________|
 *
 */
public class RequestInfo implements Serializable{
	
	private static final long serialVersionUID = 2486787412931438059L;
	
	private FileInfo fileInfo;
	private String userId;
	private ServiceNum serviceNum;
	
	public RequestInfo(FileInfo fileInfo, String userId, ServiceNum serviceNum) {
		//super();
		this.fileInfo = fileInfo;
		this.userId = userId;
		this.serviceNum = serviceNum;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}
	
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public ServiceNum getServiceNum() {
		return serviceNum;
	}
	
	public void setServiceNum(ServiceNum serviceNum) {
		this.serviceNum = serviceNum;
	}
		
}
