package db.store;

import java.util.List;

import db.domain.DirFile;
import db.domain.PathMapping;
import db.domain.User;

public class DBStore {
	//private User user;
	private DirFile dirFile;
	private PathMapping pathMapping;
	private List<DirFile> dirFileList;
	private static final DBStore instance = new DBStore();
	
	public static DBStore getInstance(){
		return instance;
	}
	
	private DBStore(){
		// ÃÊ±âÈ­
		//file read - list upload
		//
		
		
		dirFile = new DirFile();
	}
	
	/**
	 * upload / download/
	 * folder, file -> create/ delete/ modify/
	 * move/ find/ copy/ 
	 * 
	 * */
}
