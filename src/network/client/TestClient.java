package network.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import db.domain.FileInfo;
import db.domain.RequestInfo;
import network.server.ServiceNum;

public class TestClient {

	public static void main(String[] args){
		
		Socket sock = null;
		
		try {
		
			sock = new Socket("127.0.0.1", 9900);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileInfo fileInfo = new FileInfo("SB", "c:/aaa/bbb/ccc/ddd.txt", "d:/aaa/ccc/eee/r.txt");
//		FileInfo fileInfo = null;
		RequestInfo requestInfo = new RequestInfo(fileInfo, "SB", ServiceNum.UPLOAD);
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		
			while(true){	
				
				Scanner sc = new Scanner(System.in);
				sc.nextLine();
//			FileOutputStream fos = new FileOutputStream("object.dat");
				
				oos.writeObject(requestInfo);
			
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}

class TTT implements Runnable{

	@Override
	public void run() {
					
		while(true){
			Scanner sc = new Scanner(System.in);
			try {
			
				System.out.print("입력해주세요 :");
				sc.nextLine();
				throw new Exception("에러 발생!");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
}
