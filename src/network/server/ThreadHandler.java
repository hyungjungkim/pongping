package network.server;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import fileprocessor.server.ChangeNameRunnable;

public class ThreadHandler {

	  public static void main(String[] args) {

	      ExecutorService executorService = Executors.newCachedThreadPool();

	      int i=0;

	      for (i=0;i<2;i++) {
	    	  //��û���� �ҷ�����.
	        executorService.execute(new ChangeNameRunnable());  // 2���� ������ ������û

	      }      

	      // ���� Ǯ ������ ũ�� Ȯ��
	      System.out.println("cached thread pool size was "+ ((ThreadPoolExecutor) executorService).getPoolSize());

	      

	      try {Thread.sleep(70*1000);}catch(Exception e){}; // 1�� 10�� ���� ��ٸ�(60�� �� ������ Ǯ ������ ����Ȯ�� ����)
	      System.out.println("cached thread pool size was "+((ThreadPoolExecutor) executorService).getPoolSize());

	      

	      executorService.shutdown();

	      while (!executorService.isTerminated()) {
	      }
	      System.out.println("tasks are completed");
	   }
	}


