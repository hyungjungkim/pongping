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

public class DirectoryCreateRunnable implements Runnable {
	//
	private Socket sock = null;
	private FileInfo fileInfo = null;
	private ObjectOutputStream out = null;
	private QueueManager queuemanager;
	private HandleInfo handleInfo;
	private DBStore dbStore;

	public DirectoryCreateRunnable() {
		queuemanager = QueueManager.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//
		while (true) {
			try {
				Thread.sleep(10);
				this.handleInfo = queuemanager.getMkDirQueue().take();
				this.fileInfo = this.handleInfo.getFileInfo();
				this.sock = this.handleInfo.getSock();
				this.dbStore = DBStore.getInstance(fileInfo.getUserId());
				this.DirectoryCreate(this.fileInfo);
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

	public List<DirFile> DirectoryCreate(FileInfo fileInfor) throws IOException {
		// TODO client requesting path => to DB
		String clientPath = fileInfor.getCurrentPath();
		String dirCreatePath = dbStore.DirectoryCreate(clientPath); // from DB
		File file = new File(dirCreatePath);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("Directory Name = " + dirCreatePath); // dedug
		}
		String parentPath = clientPath.substring(0, clientPath.lastIndexOf("/"));
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			// Serializable
			ListInfor retList = new ListInfor();
			retList.setListInfor(dbStore.ShowList(parentPath));
			out.writeObject(retList);
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
//			out.close();
		}
		return null;
	}
}
