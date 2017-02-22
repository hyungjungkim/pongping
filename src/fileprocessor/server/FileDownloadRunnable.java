package fileprocessor.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import db.domain.FileInfo;
import db.domain.HandleInfo;
import db.store.DBStore;
import network.server.QueueManager;

public class FileDownloadRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private DataOutputStream dos = null;
	private FileInputStream fis = null;
	private DBStore dbStore;
	private QueueManager queuemanager;
	private HandleInfo handleInfo;
	
	public FileDownloadRunnable(){
		//
		queuemanager = QueueManager.getInstance();
	}
	
	@Override
	public void run() {
		//
		try{
			Thread.sleep(10);
			this.handleInfo = queuemanager.getDownloadQueue().take();
			this.fileInfo = this.handleInfo.getFileInfo();
			this.sock = this.handleInfo.getSock();
			this.dbStore = DBStore.getInstance(this.fileInfo.getUserId());
			//
			this.FileDownload(this.fileInfo);
			
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void FileDownload(FileInfo fileInfor) {
		// client requesting path => to DB
		String clientPath = fileInfor.getCurrentPath();
		String serverDownPath = dbStore.FileDownload(clientPath); // from DB
		byte[] contentBytes = new byte[1024];

		try {
			dos = new DataOutputStream(sock.getOutputStream());
			fis = new FileInputStream(serverDownPath);
			while (true) {
				int count = fis.read(contentBytes);
				if (count == -1) {
					break;
				}

				dos.write(contentBytes, 0, count);
			}
			System.out.println("server filedownload is finished");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
