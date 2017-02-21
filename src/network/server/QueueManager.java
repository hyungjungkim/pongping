import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import db.domain.HandleInfo;


public class QueueManager {
	
	private static QueueManager instance;
	
	private Queue<HandleInfo> uploadQueue;
	private Queue<HandleInfo> downloadQueue;
	private Queue<HandleInfo> mkDirQueue;
	private Queue<HandleInfo> rmvDirQueue;
	private Queue<HandleInfo> rmvFileQueue;
	private Queue<HandleInfo> cngFileNameQueue;
	private Queue<HandleInfo> cngDirNameQueue;
	private Queue<HandleInfo> searchQueue;
	private Queue<HandleInfo> showlistQueue;
	
	private QueueManager() {
		
		Queue<HandleInfo> uploadQueue = new LinkedList<>();
		Queue<HandleInfo> downloadQueue = new LinkedList<>();
		Queue<HandleInfo> mkDirQueue = new LinkedList<>();
		Queue<HandleInfo> rmvDirQueue = new LinkedList<>();
		Queue<HandleInfo> rmvFileQueue = new LinkedList<>();
		Queue<HandleInfo> cngFileNameQueue = new LinkedList<>();
		Queue<HandleInfo> cngDirNameQueue = new LinkedList<>();
		Queue<HandleInfo> searchQueue = new LinkedList<>();
		Queue<HandleInfo> showlistQueue = new LinkedList<>();

	}
	
	public static QueueManager getInstance(){
		
		if( instance == null ){
			instance = new QueueManager();
		}
		return instance;
		
	}

	public Queue<HandleInfo> getUploadQueue() {
		return uploadQueue;
	}

	public Queue<HandleInfo> getDownloadQueue() {
		return downloadQueue;
	}

	public Queue<HandleInfo> getMkDirQueue() {
		return mkDirQueue;
	}

	public Queue<HandleInfo> getRmvDirQueue() {
		return rmvDirQueue;
	}

	public Queue<HandleInfo> getRmvFileQueue() {
		return rmvFileQueue;
	}

	public Queue<HandleInfo> getCngFileNameQueue() {
		return cngFileNameQueue;
	}

	public Queue<HandleInfo> getCngDirNameQueue() {
		return cngDirNameQueue;
	}

	public Queue<HandleInfo> getSearchQueue() {
		return searchQueue;
	}

	public Queue<HandleInfo> getShowlistQueue() {
		return showlistQueue;
	}

	public void setUploadQueue(Queue<HandleInfo> uploadQueue) {
		this.uploadQueue = uploadQueue;
	}

	public void setDownloadQueue(Queue<HandleInfo> downloadQueue) {
		this.downloadQueue = downloadQueue;
	}

	public void setMkDirQueue(Queue<HandleInfo> mkDirQueue) {
		this.mkDirQueue = mkDirQueue;
	}

	public void setRmvDirQueue(Queue<HandleInfo> rmvDirQueue) {
		this.rmvDirQueue = rmvDirQueue;
	}

	public void setRmvFileQueue(Queue<HandleInfo> rmvFileQueue) {
		this.rmvFileQueue = rmvFileQueue;
	}

	public void setCngFileNameQueue(Queue<HandleInfo> cngFileNameQueue) {
		this.cngFileNameQueue = cngFileNameQueue;
	}

	public void setCngDirNameQueue(Queue<HandleInfo> cngDirNameQueue) {
		this.cngDirNameQueue = cngDirNameQueue;
	}

	public void setSearchQueue(Queue<HandleInfo> searchQueue) {
		this.searchQueue = searchQueue;
	}

	public void setShowlistQueue(Queue<HandleInfo> showlistQueue) {
		this.showlistQueue = showlistQueue;
	}

	
}