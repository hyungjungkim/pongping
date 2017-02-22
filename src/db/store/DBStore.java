package db.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	/**객체 하나하나의 변수 (table row)*/
//	private DirFile dirFile;
//	private PathMapping pathMapping;
	/**table 변수*/
	private List<DirFile> dirFileList;
	private List<PathMapping> pathMappingList;
	
	private String root="C:\\FileServer";					//Server PC root (Client root랑 달라요) 
	
	/** Store 인스턴스 (User별)*/
	private static DBStore instance;
	
	public static DBStore getInstance(String userId){
		instance = new DBStore(userId);
		return instance;
	}
	
	private DBStore(String userId){
		//회원가입시, userID 생성 및 Server 폴더 생성 할때 : DBInfo.txt / MappingInfo.txt 도 생성해야함
		//DBInfo.txt/ MappingInfo.txt => User에 DirFile table과 /Client-Server Path Mapping table 등이 저장되어 있음.
		this.userID = userId;
		
		BufferedReader br=null;
		dirFileList = new ArrayList<>();
		pathMappingList = new ArrayList<>();			
		//DBInfo.txt load
		try {
			br = new BufferedReader(new FileReader(root + "\\"+ userId + "\\DBInfo.txt"));			
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
			br = new BufferedReader(new FileReader(root+ "\\"+ userId + "\\MappingInfo.txt"));			
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

	public void ChangeName(String filePath, String newName){
		
	}
	
	public void DirecotryRemove(String filePath){
		
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
}
