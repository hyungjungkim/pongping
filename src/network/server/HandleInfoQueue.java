package network.server;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import db.domain.HandleInfo;

public class HandleInfoQueue {
	//
	private ArrayBlockingQueue<HandleInfo> queue;
	
	public HandleInfoQueue() {
		this.queue = new ArrayBlockingQueue<>(100, true);
	}

	public void put(HandleInfo handleInfo) {
		//
		this.queue.add(handleInfo);
		
	}
	
	public HandleInfo take() {
		
		try {
			
			return this.queue.take();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}
	
}
