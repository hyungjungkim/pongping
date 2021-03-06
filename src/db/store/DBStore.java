package db.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.domain.DirFile;
import db.domain.PathMapping;

public class DBStore{
	private static final int SimpleDateFormat = 0;
	private String userID;
	/**table */
	private List<DirFile> dirFileList;
	private List<PathMapping> pathMappingList;

	/** File IO */
	private File dbFile;
	private File mappingFile;

	private final String root="C:\\FileServer";               //Server PC root
	public final String MAPPINGInfo = "MappingInfo.txt";
	public final String DBInfo ="DBInfo.txt";

	public DBStore(String userId){
		this.userID = userId;

		BufferedReader br=null;
		dirFileList = new ArrayList<>();
		pathMappingList = new ArrayList<>();         
		//DBInfo.txt load
		try {
			System.out.println("DBStore 생성자 ");
			br = new BufferedReader(new FileReader(root + "/"+ userId + "/"+DBInfo));
			String str = null;
			while((str = br.readLine())!=null){
				//Parsing
				String[] parse_str = str.split(",");
				DirFile dirFile = new DirFile(Integer.parseInt(parse_str[0]), parse_str[1], parse_str[2], Integer.parseInt(parse_str[3]), parse_str[4], Integer.parseInt(parse_str[5]), parse_str[6]);
				dirFileList.add(dirFile);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2){
			e2.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}

		//MappingInfo.txt load
		try {
			br = new BufferedReader(new FileReader(root+ "\\"+ userId + "\\"+MAPPINGInfo));         
			String str = null;
			while((str = br.readLine())!=null){
				//Parsing
				String[] parse_str = str.split(",");
				PathMapping pathMapping = new PathMapping(Integer.parseInt(parse_str[0]), parse_str[1], parse_str[2], parse_str[3],parse_str[4]);
				pathMappingList.add(pathMapping);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2){
			e2.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * DirFile (int index, String fileName, String userId, int parentDirIdx, String modifiedDate, int flag, String clientPath)
	 * PathMapping (int idx, String userId, String originFileName, String serverFileName, String serverPath)
	 * upload / download/
	 * folder, file -> create/ delete/ modify/
	 * move/ find/ copy/ 
	 * showList 
	 *    
	 * C:\FileServer\0001
	 * */

	public String FileUpload(String filePath){
		String serverPath =root+"\\"+userID;
		String[] parse = filePath.split("/");
		//parentIndex search
		int parentIdx = getParentIndex(filePath);

		//ModifiedDate
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//DirFile add
		DirFile dirFile = new DirFile(dirFileList.size(), parse[parse.length-1], userID, parentIdx,sdf.format(today) , 1, filePath);
		dirFileList.add(dirFile);

		String newFileName =  System.currentTimeMillis()+"_"+dirFile.getFileName();

		serverPath += "\\"+parse[parse.length-1].charAt(0)+"\\"+newFileName;
		//PathMapping add
		PathMapping pathMapping = new PathMapping(dirFile.getIndex(), userID, dirFile.getFileName(),newFileName, serverPath);
		pathMappingList.add(pathMapping);
		
		updateInfoFile(dirFile, pathMapping);//dbFile update

		//System.out.println("DBStore FileUpload Return : "+serverPath);
		return serverPath;
	}

	public String FileDownload(String filePath){
		for(DirFile d : dirFileList){
			if(filePath.equals(d.getClientPath())){
				for(PathMapping p : pathMappingList){
					if(p.getDirIdx()==d.getIndex()){
						return p.getServerPath();
					}
				}
			}
		}
		return null;
	}

	public String FileRemove(String filePath){
		String[] parse = filePath.split("/");
		List<DirFile> array = FileSearch(parse[parse.length-1]);
		int parentIdx = getParentIndex(filePath);
		String serverPath = "";
		for(DirFile d : array){
			if(d.getFlag()==1){
				if(parentIdx == d.getParentDirIdx()){
					for(PathMapping p : pathMappingList)
					{
						if(d.getIndex() == p.getDirIdx())
						{
							serverPath = p.getServerPath();
							dirFileList.remove(d);
							pathMappingList.remove(p);
							break;
						}
					}
					writeInfoFile(); 			// Write Info Files.
					return serverPath;
				}
			}
		}
		return serverPath;
	}

	public List<DirFile> FileSearch(String fileName){
		List<DirFile> resultFile = new ArrayList<>();
		int cnt =0;
		while(dirFileList.size()>cnt){
			if(fileName.equals(dirFileList.get(cnt).getFileName())){
				resultFile.add(dirFileList.get(cnt));
			}
			cnt++;
		}
		return resultFile;
	}

	public String DirectoryCreate(String filePath){
		String[] parse = filePath.split("/");
		int parentIdx = getParentIndex(filePath);
		//ModifiedDate
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String[] pars =filePath.split("/");
		String dirName =pars[pars.length-1];

		DirFile dirFile = new DirFile(dirFileList.size(), dirName, userID, parentIdx, sdf.format(today), 0, filePath);
		dirFileList.add(dirFile);

		String serverFileName =  System.currentTimeMillis()+"_"+dirFile.getFileName();
		String serverPath =root+"\\"+userID+"\\"+parse[parse.length-1].charAt(0)+"\\"+serverFileName;
		PathMapping pathMapping = new PathMapping(dirFile.getIndex(), userID, dirFile.getFileName(), serverFileName, serverPath );
		pathMappingList.add(pathMapping);
		
		updateInfoFile(dirFile, pathMapping);

		return serverPath;
	}

	public void ChangeName(String filePath, String newName){
		int index =0;
		for(DirFile d : dirFileList){
			if(filePath.equals(d.getClientPath())){
				index = d.getIndex();
				d.setFileName(newName);
				String[] parse = filePath.split("/");
				String newclientPath = "";
				for(int i=0; i<parse.length-1; i++)
				{
					newclientPath += parse[i] + "/";
				}
				newclientPath += newName;
				d.setClientPath(newclientPath);
				writeInfoFile();
			}
		}
	}

	public List<DirFile> DirecotryAllRemove(String filePath){
		String[] parse = filePath.split("/");
		List<DirFile> removeFiles = new ArrayList<>();
		int index =0;
		for(DirFile d : dirFileList){
			if(filePath.equals(d.getClientPath())){
				index = d.getIndex();
			}
		}
		for(DirFile d : dirFileList){
			if(index == d.getParentDirIdx()){
				removeFiles.add(d);
			}
		}

		return removeFiles;
	}

	public String DirecotryRemove(String filePath){
		String[] parse = filePath.split("/");
		List<DirFile> array = FileSearch(parse[parse.length-1]);
		int parentIdx = getParentIndex(filePath);
		String serverPath = "";
		for(DirFile d : array){
			if(d.getFlag()==0){
				if(parentIdx == d.getParentDirIdx()){
					for(PathMapping p : pathMappingList)
					{
						if(d.getIndex() == p.getDirIdx())
						{
							serverPath = p.getServerPath();
							dirFileList.remove(d);
							pathMappingList.remove(p);
							break;
						}
					}
					writeInfoFile();
					return serverPath;
				}
			}
		}
		return serverPath;
	}

	public List<DirFile> ShowList(String filePath){
		List<DirFile> result =new ArrayList<>();
		int parentIdx =getParentIndex(filePath);
		for(DirFile d: dirFileList){
			if(d.getParentDirIdx()==parentIdx){
				result.add(d);
			}
		}
		return result;
	}


	public void FileCopy(String filePath, String newFilePath){

	}
	public void FileMove(String filePath, String newFilePath){

	}

	private int getParentIndex(String filePath){
		int parentIdx = 0;
		String str = "";
		String[] parse = filePath.split("/");
		for(int i=0; i<parse.length-1; i++){
			str += (parse[i]+"/");
		}
		for(DirFile temp : dirFileList){
			if(str.equals(temp.getClientPath()+"/")){
				return parentIdx = temp.getIndex();
			}
		}
		return parentIdx;
	}
	
	// if create File or folder
	private void updateInfoFile(DirFile dirFile, PathMapping pathMapping) {
		
		FileWriter dbFileWriter;
		FileWriter pathFileWriter;

		try {
			dbFileWriter = new FileWriter(root + "\\"+ userID + "\\"+DBInfo, true);
			dbFileWriter.write(dirFile.toString());
			dbFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pathFileWriter = new FileWriter(root + "\\"+ userID + "\\"+MAPPINGInfo, true);
			pathFileWriter.write(dirFile.toString());
			pathFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// if delete file of remove Dir (changeName), Write Info Files
	public void writeInfoFile() {

		FileWriter dbFileWriter;
		FileWriter pathFileWriter;

		try {
			dbFileWriter = new FileWriter(root + "\\"+ userID + "\\"+DBInfo);
			for(int i=0; i<dirFileList.size();i++){
				dbFileWriter.write(dirFileList.get(i).toString()+"\n"); //List �엳�뒗 紐⑤뱺 �궡�슜�뱾�쓣 �뙆�씪�뿉 write //DirFile, PathMapping -> toString
			}
			dbFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			pathFileWriter = new FileWriter(root + "\\"+ userID + "\\"+MAPPINGInfo);
			for(int i=0; i<pathMappingList.size(); i++){
				pathFileWriter.write(pathMappingList.get(i).toString()+"\n");
			}
			pathFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readFile(){
		BufferedReader dbFileReader;
		BufferedReader pathFileReader;

		try {
			dbFileReader = new BufferedReader(new FileReader(root + "\\"+ userID + "\\"+DBInfo));
			String str="";
			while((str=dbFileReader.readLine())!=null){
				System.out.println("DBInfo File : "+str);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		System.out.println();
		
		try {
			pathFileReader = new BufferedReader(new FileReader(root + "\\"+ userID + "\\"+MAPPINGInfo));
			String str="";
			while((str=pathFileReader.readLine())!=null){
				System.out.println("MappingInfo File : "+str);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}