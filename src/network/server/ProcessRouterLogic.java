package network.server;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.HandleInfo;
import db.domain.RequestInfo;
import db.store.DBStore;

public class ProcessRouterLogic extends Thread implements ProcessRouter {
	
	private String userId;
	private ServiceNum serviceNum;
	private FileInfo fileInfo;
	private Socket sock;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
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
		System.out.println("userId : " + this.userId);
		this.serviceNum=requestInfo.getServiceNum();
		System.out.println("serviceNum : " + this.serviceNum);
		this.fileInfo=requestInfo.getFileInfo();
		System.out.println("fileInfo : " + toString());
		
	}

	/***
	 * 
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		super.run();
//		ObjectInputStream in = null;
//		ObjectOutputStream out = null;
		try {
			in = new ObjectInputStream(sock.getInputStream());
			out = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true){
			
			HandleInfo handleInfo = null;
			try {
//				FileInputStream fis = new FileInputStream("objectfile.ser");
				
//				System.out.println("inputaaa");
				RequestInfo requestInfo = (RequestInfo)in.readObject();
				System.out.println(requestInfo.getUserId());
				this.depacketizer(requestInfo);
				handleInfo = new HandleInfo(sock, fileInfo, in, out);
				System.out.println(handleInfo.getFileInfo().getCurrentPath());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//FileServerLogic fileServerLogic=new FileServerLogic(sock);
			QueueManager queuemanager = QueueManager.getInstance();
			
			if(this.serviceNum.equals(ServiceNum.UPLOAD)){
				//fileServerLogic.FileUpload(fileInfo);
//				System.out.println(fileInfo.getCurrentPath()+"이게 null???");
				queuemanager.getUploadQueue().put(handleInfo);
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