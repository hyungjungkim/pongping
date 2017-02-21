package fileprocessor.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import db.domain.FileInfo;

public class FileDownloadRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private DataOutputStream dos = null;
	private FileInputStream fis = null;
	
	public FileDownloadRunnable(FileInfo fileInfo , Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			this.FileDownload(this.fileInfo);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void FileDownload(FileInfo fileInfor) throws IOException {
		// TODO client requesting path => to DB
		String serverDownPath = null; // from DB
		byte[] contentBytes = new byte[1024];

		try {
			dos = new DataOutputStream(sock.getOutputStream());
			fis = new FileInputStream(serverDownPath);
			while (true) {
				int count = fis.read(contentBytes);
				if (count == -1) {
					break;
				}

				dos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		// TODO
	}
}
