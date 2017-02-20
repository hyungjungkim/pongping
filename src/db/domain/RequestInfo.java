package db.domain;

import network.server.ServiceNum;

/**
 * @author 강승보, 방주선
 * 파일 경로 및 사용자 id, 서비스 번호를 가지고 있는 클래스
 * ______________________________________________________
 * |  ___________________________         RequestInfo   |
 * |  |FileInfo    |            |                       |
 * |  |   파일경로     | 사용자ID    |   사용자ID, 서비스번호      |
 * |  |            |            |                       |
 * |  |____________|____________|                       |
 * |____________________________________________________|
 *
 */
public class RequestInfo {
	
	private FileInfo fileInfo;
	private String userId;
	private ServiceNum serviceNum;
		
}
