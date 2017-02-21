package network.server;

import java.util.ArrayList;

import db.domain.HandleInfo;

public class QueueManager {
	
	private static QueueManager instance;
	
	private HandleInfoQueue uploadQueue;
	private HandleInfoQueue downloadQueue;
	private HandleInfoQueue mkDirQueue;
	private HandleInfoQueue rmvDirQueue;
	private HandleInfoQueue rmvFileQueue;
	private HandleInfoQueue cngFileNameQueue;
	private HandleInfoQueue cngDirNameQueue;
	private HandleInfoQueue searchQueue;
	private HandleInfoQueue showlistQueue;
	
	private QueueManager() {
		
		this.uploadQueue = new HandleInfoQueue();
		this.downloadQueue = new HandleInfoQueue();
		this.mkDirQueue = new HandleInfoQueue();
		this.rmvDirQueue = new HandleInfoQueue();
		this.rmvFileQueue = new HandleInfoQueue();
		this.cngFileNameQueue = new HandleInfoQueue();
		this.cngDirNameQueue = new HandleInfoQueue();
		this.searchQueue = new HandleInfoQueue();
		this.showlistQueue = new HandleInfoQueue();
	
	}
	
	public static QueueManager getInstance(){
		
		if( instance == null ){
			instance = new QueueManager();
		}
		return instance;
		
	}
	
	public HandleInfoQueue getUploadQueue() {
		return uploadQueue;
	}

	public HandleInfoQueue getDownloadQueue() {
		return downloadQueue;
	}

	public HandleInfoQueue getMkDirQueue() {
		return mkDirQueue;
	}

	public HandleInfoQueue getRmvDirQueue() {
		return rmvDirQueue;
	}

	public HandleInfoQueue getRmvFileQueue() {
		return rmvFileQueue;
	}

	public HandleInfoQueue getCngFileNameQueue() {
		return cngFileNameQueue;
	}

	public HandleInfoQueue getCngDirNameQueue() {
		return cngDirNameQueue;
	}

	public HandleInfoQueue getSearchQueue() {
		return searchQueue;
	}

	public HandleInfoQueue getShowlistQueue() {
		return showlistQueue;
	}

	public void setUploadQueue(HandleInfoQueue uploadQueue) {
		this.uploadQueue = uploadQueue;
	}

	public void setDownloadQueue(HandleInfoQueue downloadQueue) {
		this.downloadQueue = downloadQueue;
	}

	public void setMkDirQueue(HandleInfoQueue mkDirQueue) {
		this.mkDirQueue = mkDirQueue;
	}

	public void setRmvDirQueue(HandleInfoQueue rmvDirQueue) {
		this.rmvDirQueue = rmvDirQueue;
	}

	public void setRmvFileQueue(HandleInfoQueue rmvFileQueue) {
		this.rmvFileQueue = rmvFileQueue;
	}

	public void setCngFileNameQueue(HandleInfoQueue cngFileNameQueue) {
		this.cngFileNameQueue = cngFileNameQueue;
	}

	public void setCngDirNameQueue(HandleInfoQueue cngDirNameQueue) {
		this.cngDirNameQueue = cngDirNameQueue;
	}

	public void setSearchQueue(HandleInfoQueue searchQueue) {
		this.searchQueue = searchQueue;
	}

	public void setShowlistQueue(HandleInfoQueue showlistQueue) {
		this.showlistQueue = showlistQueue;
	}

}