package network.server;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import db.domain.FileInfo;

public class ThreadPoolHandler {

	private ThreadPoolExecutor tpe;
	private int threadsNum;
	
	public ThreadPoolHandler(){
		
		this.tpe = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		this.threadsNum = 0;
	}
	
	public void startThread(Socket sock, FileInfo fileInfo){
		
		while (true) {
			String string = (String) scan.next();
			for (int i = 0; i < size; i++) {
				tpe.execute(new People(i + 1));
			}
		}
		
	}

	public int getThreadNum() {
		
		return threadsNum;
		
	}

}
