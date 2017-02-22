package fileprocessor.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import db.domain.FileInfo;
import db.store.DBStore;

public class FileDownloadRunnable implements Runnable{
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private DataOutputStream dos = null;
	private FileInputStream fis = null;
	private DBStore dbStore;
	
	public FileDownloadRunnable(FileInfo fileInfo , Socket sock){
		this.fileInfo = fileInfo;
		this.sock = sock;
		this.dbStore = DBStore.getInstance(fileInfo.getUserId());
	}
	@Override
	public void run() {
		try{
			this.FileDownload(this.fileInfo);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void FileDownload(FileInfo fileInfor) throws IOException {
		// client requesting path => to DB
		String clientPath = fileInfor.getCurrentPath();
		String serverDownPath = dbStore.FileDownload(clientPath); // from DB
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
			System.out.println("server filedownload is finished");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
