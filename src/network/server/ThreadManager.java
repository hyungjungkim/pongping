package network.server;

import java.util.List;

import fileprocessor.server.ChangeNameRunnable;
import fileprocessor.server.DirectoryCreateRunnable;

public class ThreadManager {
	//
	private List<Thread> changeNameRunnables;
	private List<Thread> directoryCreateRunnables;
	private List<Thread> directoryRemoveRunnables;
	private List<Thread> fileDownloadRunnables;
	private List<Thread> fileRemoveRunnables;
	private List<Thread> fileSearchRunnables;
	private List<Thread> fileUploadRunnables;
	private List<Thread> showListRunnables;
	
	
	
	public ThreadManager(){
		
	}
	
	public void runThread(){
		
		Thread handlerThread; 
				
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			DirectoryCreateRunnable directoryCreateRunnable = new DirectoryCreateRunnable();
			handlerThread = new Thread(directoryCreateRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
			this.changeNameRunnables.add(handlerThread);
			
		}
		
	}
	
}
