package db.domain;

import java.io.Serializable;

import network.server.ServiceNum;

/**
 * @author ���º�, ���ּ�
 * ���� ��� �� ����� id, ���� ��ȣ�� ������ �ִ� Ŭ����
 * ______________________________________________________
 * |  ___________________________         RequestInfo   |
 * |  |FileInfo    |            |                       |
 * |  |   ���ϰ��     | �����ID    |   �����ID, ���񽺹�ȣ      |
 * |  |            |            |                       |
 * |  |____________|____________|                       |
 * |____________________________________________________|
 *
 */
public class RequestInfo implements Serializable{
	
	private FileInfo fileInfo;
	private String userId;
	private ServiceNum serviceNum;
	
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
