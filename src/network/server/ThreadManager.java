package network.server;

import java.util.List;

import fileprocessor.server.ChangeNameRunnable;
import fileprocessor.server.DirectoryCreateRunnable;
import fileprocessor.server.DirectoryRemoveRunnable;
import fileprocessor.server.FileDownloadRunnable;
import fileprocessor.server.FileRemoveRunnable;
import fileprocessor.server.FileSearchRunnable;
import fileprocessor.server.FileUploadRunnable;
import fileprocessor.server.ShowListRunnable;

public class ThreadManager {
	//
//	private List<Thread> changeNameRunnables;
//	private List<Thread> directoryCreateRunnables;
//	private List<Thread> directoryRemoveRunnables;
//	private List<Thread> fileDownloadRunnables;
//	private List<Thread> fileRemoveRunnables;
//	private List<Thread> fileSearchRunnables;
//	private List<Thread> fileUploadRunnables;
//	private List<Thread> showListRunnables;
	
	
	
	public ThreadManager(){
		
	}
	
	public void runThread(){
		
		Thread handlerThread; 
				
		for(int i = 0; i < 3; i++){
			
			ChangeNameRunnable changeNameRunnable = new ChangeNameRunnable();
			handlerThread = new Thread(changeNameRunnable);
//			this.changeNameRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			DirectoryCreateRunnable directoryCreateRunnable = new DirectoryCreateRunnable();
			handlerThread = new Thread(directoryCreateRunnable);
//			this.directoryCreateRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			DirectoryRemoveRunnable directoryRemoveRunnable = new DirectoryRemoveRunnable();
			handlerThread = new Thread(directoryRemoveRunnable);
//			this.directoryRemoveRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			FileDownloadRunnable fileDownloadRunnable = new FileDownloadRunnable();
			handlerThread = new Thread(fileDownloadRunnable);
//			this.fileDownloadRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			FileRemoveRunnable fileRemoveRunnable = new FileRemoveRunnable();
			handlerThread = new Thread(fileRemoveRunnable);
//			this.fileRemoveRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			FileSearchRunnable fileSearchRunnable = new FileSearchRunnable();
			handlerThread = new Thread(fileSearchRunnable);
//			this.fileSearchRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			FileUploadRunnable fileUploadRunnable = new FileUploadRunnable();
			handlerThread = new Thread(fileUploadRunnable);
//			this.fileUploadRunnables.add(handlerThread);
			
		}
		
		for(int i = 0; i < 3; i++){
			
			ShowListRunnable showListRunnable = new ShowListRunnable();
			handlerThread = new Thread(showListRunnable);
//			this.showListRunnables.add(handlerThread);
			
		}
		
	}
	
}
