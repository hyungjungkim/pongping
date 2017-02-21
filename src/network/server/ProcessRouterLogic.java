package network.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.RequestInfo;
import fileprocessor.server.FileServerLogic;

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
			
			
			try {
				FileInputStream fis = new FileInputStream("objectfile.ser");
				ObjectInputStream in = new ObjectInputStream(fis);
				RequestInfo requestInfo = (RequestInfo)in.readObject();
				this.depacketizer(requestInfo);
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			FileServerLogic fileServerLogic=new FileServerLogic(sock);
			if(this.serviceNum == ServiceNum.UPLOAD){
				fileServerLogic.FileUpload(fileInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.DOWNLOAD)){
				fileServerLogic.FileDownload(fileInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.MKDIR)){
				fileServerLogic.DirectoryCreate(fileInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.RMVDIR)){
				fileServerLogic.DirectoryRemove(fileInfo);
			}
			else if(this.serviceNum.equals(ServiceNum.RMFILE)){
				fileServerLogic.FileRemove(userId, fileInfo.getCurrentPath());
			}
			else if(this.serviceNum.equals(ServiceNum.CNGFILENAME)){
				fileServerLogic.ChangeName(userId, fileInfo.getCurrentPath(), fileInfo.getNewPath());
			}
			else if(this.serviceNum.equals(ServiceNum.SEARCH)){
				fileServerLogic.FileSearch(userId, fileInfo.getCurrentPath());
				//when Searching you can use the information at CurrentPath as fileName.
			}
			else if(this.serviceNum.equals(ServiceNum.SHOWLIST)){
				fileServerLogic.ShowList(userId,fileInfo.getCurrentPath());
			}
			
		}
		
	}

}
