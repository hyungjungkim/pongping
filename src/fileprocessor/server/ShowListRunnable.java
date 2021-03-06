package fileprocessor.server;

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

public class ShowListRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private DBStore dbStore;
	private HandleInfo handleInfo;
	private QueueManager queuemanager;
	private DBStoreFactory factory;
	
	public ShowListRunnable() {
		//
		queuemanager = QueueManager.getInstance();
		factory = DBStoreFactory.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
				this.handleInfo = queuemanager.getShowlistQueue().take();
				this.fileInfo = this.handleInfo.getFileInfo();
				this.sock = this.handleInfo.getSock();
				this.dbStore = factory.getDBStoreInstance(fileInfo.getUserId());
				System.out.println("servershowlist called");
				out = handleInfo.getOut();
				this.ShowList(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// TODO Stop
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> ShowList(String userId, String currentPath) throws IOException {
		//
		try {
			//out = new ObjectOutputStream(sock.getOutputStream());
			// Serializable
			ListInfor retList = new ListInfor();
			System.out.println(currentPath);
			retList.setListInfor(dbStore.ShowList(currentPath));
			System.out.println(dbStore.ShowList(currentPath).get(0).toString());
			out.writeObject(retList);
			out.flush();
			System.out.println(retList.getListInfor().get(0).toString());
		} catch (IOException e) {
			System.out.println("showlistserver: "+e.getMessage());
			e.getStackTrace();
		} finally {
//			out.close();
		}
		return null;
	}
}
