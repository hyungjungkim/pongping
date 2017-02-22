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
	    	  //요청별로 불러야함.
	        executorService.execute(new ChangeNameRunnable());  // 2개의 쓰레드 생성요청

	      }      

	      // 현재 풀 사이즈 크기 확인
	      System.out.println("cached thread pool size was "+ ((ThreadPoolExecutor) executorService).getPoolSize());

	      

	      try {Thread.sleep(70*1000);}catch(Exception e){}; // 1분 10초 동안 기다림(60초 후 쓰레드 풀 사이즈 변경확인 위해)
	      System.out.println("cached thread pool size was "+((ThreadPoolExecutor) executorService).getPoolSize());

	      

	      executorService.shutdown();

	      while (!executorService.isTerminated()) {
	      }
	      System.out.println("tasks are completed");
	   }
	}


