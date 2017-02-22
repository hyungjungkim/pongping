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

public class DirectoryRemoveRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private HandleInfo handleInfo;
	private QueueManager queuemanager;
	private DBStore dbStore;
	
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
				this.dbStore = DBStore.getInstance(fileInfo.getUserId());
				this.out = this.handleInfo.getOut();
				this.DirectoryRemove(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DirFile> DirectoryRemove(String userId, String currentPath) throws IOException {
		List<DirFile> removeFiles = dbStore.DirecotryAllRemove(currentPath);
		if(removeFiles==null){
			// �ش� ���� ����
			String serverPath = dbStore.DirecotryRemove(currentPath);
			serverFileRemove(serverPath);
		}else{
			for(int i=0;i<removeFiles.size();i++){
				if(removeFiles.get(i).getFlag()==0){
					// folder
					DirectoryRemove(userId, removeFiles.get(i).getClientPath());
				}else{
					// files
					String serverPath = dbStore.FileRemove(removeFiles.get(i).getClientPath());
					serverFileRemove(serverPath);
				}
			}
		}
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
	
	public void serverFileRemove(String serverPath){
		File file = new File(serverPath);
		if (file.delete()) {
			System.out.println(file.getName() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed.");
		}
	}
}
