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
import network.server.QueueManager;

public class FileRemoveRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private DBStore dbStore;
	private HandleInfo handleInfo;
	private QueueManager queuemanager;

	public FileRemoveRunnable() {
		queuemanager = QueueManager.getInstance();

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
				this.handleInfo = queuemanager.getRmvFileQueue().take();
				this.fileInfo = this.handleInfo.getFileInfo();
				this.sock = this.handleInfo.getSock();
				this.dbStore = DBStore.getInstance(fileInfo.getUserId());
				this.out = this.handleInfo.getOut();
				this.FileRemove(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// TODO Stop()
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> FileRemove(String userId, String currentPath) throws IOException {
		// client requesting path => to DB
		String clientPath = currentPath;
		String fileRemovePath = dbStore.FileRemove(clientPath); // from DB
		File file = new File(fileRemovePath);
		if (file.delete()) {
			System.out.println(file.getName() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed.");
		}
		// current list of current depth (from DB)
		String parentPath = clientPath.substring(0, clientPath.lastIndexOf("/"));
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
