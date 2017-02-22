package fileprocessor.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import db.domain.DirFile;
import db.domain.FileInfo;
import db.domain.ListInfor;
import db.store.DBStore;

public class ShowListRunnable implements Runnable {
   //
   private FileInfo fileInfo = null;
   private Socket sock = null;
   private ObjectOutputStream out = null;
   private DBStore dbStore;

   public ShowListRunnable(FileInfo fileInfo, Socket sock) {
      this.fileInfo = fileInfo;
      this.sock = sock;
      this.dbStore = DBStore.getInstance(fileInfo.getUserId());
   }

   @Override
   public void run() {
      // TODO Auto-generated method stub
      try {
         this.ShowList(this.fileInfo.getUserId(), this.fileInfo.getCurrentPath());
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public List<DirFile> ShowList(String userId, String currentPath) throws IOException {
      //
      try {
         out = new ObjectOutputStream(sock.getOutputStream());
         // Serializable
         ListInfor retList = new ListInfor();
         retList.setListInfor(dbStore.ShowList(currentPath));
         out.writeObject(retList);
      } catch (IOException e) {
         e.getStackTrace();
      } finally {
         out.close();
      }
      return null;
   }
}