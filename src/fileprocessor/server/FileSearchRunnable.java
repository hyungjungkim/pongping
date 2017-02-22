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
import network.server.QueueManager;

public class FileSearchRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private DBStore dbStore;
	private HandleInfo handleInfo;
	private QueueManager queuemanager;

	public FileSearchRunnable() {
		//
		queuemanager = QueueManager.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
				this.handleInfo = queuemanager.getSearchQueue().take();
				this.fileInfo = this.handleInfo.getFileInfo();
				this.sock = this.handleInfo.getSock();
				this.dbStore = DBStore.getInstance(fileInfo.getUserId());
				this.out = this.handleInfo.getOut();
				this.FileSearch(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// TODO Stop()
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> FileSearch(String userId, String searchName) throws IOException {
		//
		try {
			// Serializable
			ListInfor retList = new ListInfor();
			retList.setListInfor(dbStore.FileSearch(searchName));
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
