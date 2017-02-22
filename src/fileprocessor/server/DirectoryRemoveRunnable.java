package fileprocessor.server;

import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.HandleInfo;
import network.server.QueueManager;

public class DirectoryRemoveRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private HandleInfo handleInfo;
	private QueueManager queuemanager;

	public DirectoryRemoveRunnable() {
		queuemanager = QueueManager.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//
		while (true) {
			try {
				Thread.sleep(10);
				this.handleInfo = queuemanager.getRmvDirQueue().take();
				this.fileInfo = this.handleInfo.getFileInfo();
				this.sock = this.handleInfo.getSock();
				this.DirectoryRemove(this.fileInfo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> DirectoryRemove(FileInfo fileInfor) {
		// TODO client requesting path => to DB
		String serverPath = null; // from DB
		return null;
	}
}
