package network.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.HandleInfo;
import db.domain.RequestInfo;

public class ProcessRouterLogic extends Thread implements ProcessRouter {
	
	private String userId;
	private ServiceNum serviceNum;
	private FileInfo fileInfo;
	private Socket sock;
	
	public ProcessRouterLogic(Socket sock){
		
		this.sock =sock;
		
	}
	
	/***
	 * 요청번호를 받아서 ServiceNum(열거형)을 리턴함
	 */
	@Override
	public void depacketizer(RequestInfo requestInfo) {
		// TODO Auto-generated method stub
		this.userId=requestInfo.getUserId();
		this.serviceNum=requestInfo.getServiceNum();
		this.fileInfo=requestInfo.getFileInfo();
	}

	/***
	 * 
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			
			HandleInfo handleInfo = null;
			try {
				FileInputStream fis = new FileInputStream("objectfile.ser");
				ObjectInputStream in = new ObjectInputStream(fis);
				RequestInfo requestInfo = (RequestInfo)in.readObject();
				this.depacketizer(requestInfo);
				handleInfo = new HandleInfo(sock, fileInfo);
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//FileServerLogic fileServerLogic=new FileServerLogic(sock);
			
			QueueManager queuemanager = QueueManager.getInstance();
			
			if(this.serviceNum == ServiceNum.UPLOAD){
				//fileServerLogic.FileUpload(fileInfo);
				queuemanager.getCngDirNameQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.DOWNLOAD)){
				//fileServerLogic.FileDownload(fileInfo);
				queuemanager.getDownloadQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.MKDIR)){
				//fileServerLogic.DirectoryCreate(fileInfo);
				queuemanager.getMkDirQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.RMVDIR)){
				//fileServerLogic.DirectoryRemove(fileInfo);
				queuemanager.getRmvDirQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.RMFILE)){
				//fileServerLogic.FileRemove(userId, fileInfo.getCurrentPath());
				queuemanager.getRmvFileQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.CNGFILENAME)){
				//fileServerLogic.ChangeName(userId, fileInfo.getCurrentPath(), fileInfo.getNewPath());
				queuemanager.getCngFileNameQueue().put(handleInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.SEARCH)){
				//fileServerLogic.FileSearch(userId, fileInfo.getCurrentPath());
				queuemanager.getSearchQueue().put(handleInfo);
				//when Searching you can use the information at CurrentPath as fileName.
			}
			else if(this.serviceNum.equals(ServiceNum.SHOWLIST)){
				//fileServerLogic.ShowList(userId,fileInfo.getCurrentPath());
				queuemanager.getShowlistQueue().put(handleInfo);
			}
			
		}
		
	}

}
