package fileprocessor.server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;
import db.store.DBStore;

public class FileUploadRunnable implements Runnable {
	//
	private FileInfo fileInfo = null;
	private Socket sock = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream ois = null;
	private DataInputStream dis = null;
	private FileOutputStream fos = null;
	private DBStore dbStore;

	public FileUploadRunnable(FileInfo fileInfo, Socket sock) {
		this.fileInfo = fileInfo;
		this.sock = sock;
		this.dbStore = DBStore.getInstance(fileInfo.getUserId());
	}

	@Override
	public void run() {
		try {
			this.FileUpload(this.fileInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<DirFile> FileUpload(FileInfo fileInfor) throws IOException {
		String clientPath = fileInfor.getCurrentPath();
		String serverSavePath = dbStore.FileUpload(clientPath); // fromDBStore
		out = new ObjectOutputStream(sock.getOutputStream());
		byte[] contentBytes = new byte[1024];

		try {
			dis = new DataInputStream(sock.getInputStream());
			fos = new FileOutputStream(serverSavePath);
			while (true) {
				int count = dis.read(contentBytes);
				if (count == -1) {
					break;
				}
				fos.write(contentBytes, 0, count);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			dis.close();
			fos.close();
		}
		// current list of current depth (from DB) // dbstore에 디렉토리까지 주기
		String parentPath = clientPath.substring(0,clientPath.lastIndexOf("/"));
		// Serializable
		try {
			ListInfor retList = new ListInfor();
			retList.setListInfor(dbStore.ShowList(parentPath));
			out.writeObject(retList);
		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			out.close();
		}
		return null;
	}
}
