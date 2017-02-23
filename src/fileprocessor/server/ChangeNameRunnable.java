package fileprocessor.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.HandleInfo;
import db.domain.ListInfor;
import db.store.DBStore;
import db.store.DBStoreFactory;
import network.server.QueueManager;

public class ChangeNameRunnable implements Runnable {
	//
	private Socket sock = null;
	private FileInfo fileInfo = null;
	private ObjectOutputStream out = null;
	private HandleInfo handleinfo;
	private QueueManager queuemanager;
	private DBStore dbStore;
	private DBStoreFactory factory;
	
	public ChangeNameRunnable() {
		// Constructor
		queuemanager = QueueManager.getInstance();
		factory = DBStoreFactory.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
				this.handleinfo = queuemanager.getCngDirNameQueue().take();
				this.sock = this.handleinfo.getSock();
				this.fileInfo = this.handleinfo.getFileInfo();
				this.dbStore = factory.getDBStoreInstance(fileInfo.getUserId());
				this.out = this.handleinfo.getOut();
				//
				this.ChangeName(fileInfo.getUserId(), this.fileInfo.getCurrentPath(), this.fileInfo.getNewPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// TODO stop()
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> ChangeName(String userId, String currentPath, String newPath) throws IOException {
		String fileName = newPath.substring(newPath.lastIndexOf("/"), newPath.length());
		dbStore.ChangeName(currentPath, fileName);
		
		String parentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
		// Serializable
		try {			
			ListInfor retList = new ListInfor();
			retList.setListInfor(dbStore.ShowList(parentPath));
			out.writeObject(retList);
			out.flush();
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
//			out.close();
		}
		return null;
	}
}