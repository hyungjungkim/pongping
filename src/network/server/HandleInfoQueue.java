package network.server;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import db.domain.HandleInfo;

public class HandleInfoQueue {
	//
	private Queue<HandleInfo> queue = new ArrayBlockingQueue<>(100);
	
	public HandleInfoQueue() {
	
	}

	public void put(HandleInfo handleInfo) {
		//
		this.queue.add(handleInfo);
		
	}
	
	public HandleInfo take() {
		
		return this.queue.poll();
		
	}
	
}
