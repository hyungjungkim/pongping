package DB;

import java.io.File;
import java.util.ArrayList;

public class DirFile extends File{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	user ID */
	private String Id;		
	/**file Path*/
	private String path;
	/**flag 0=folder, 1=file*/
	private FileObjectIdentifier foi;
	/** file */
	private File file;
	/** DirFile List*/
	private ArrayList<DirFile> children;
	
	/**
	 *Constructor : File class Extension.
	 *Create new File.
	 * @param pathname
	 */
	public DirFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
		this.file = new File(pathname);
		this.path = pathname;
	}
	
	public String getName(){
		return this.file.getName();
	}
		
	/**
	 * To do
	 * file renameTo(File file)
	 * param: file, name
	 * */	
	
	/**
	 * 현재 경로내에, 하위 폴더 및 파일 저장 리스트
	 * @param dirFile
	 */
	public void addChild(DirFile dirFile){
		this.children.add(dirFile);
	}
	
	/**
	 * 현재 경로내에 하위 폴더&파일들 리턴 .
	 */
	public ArrayList<DirFile> getChildren(){
		return this.children;
	}

	/**
	 * 현재 경로내에,name과 같은 하위 폴더 또는 파일 리턴 .
	 * @param name
	 */
	public DirFile getChild(String name){
		for(DirFile d : children)
            if(d.getName().equals(name))
                return d;

        return null;

	}
	
	/**
	 * 현재 경로 반환 
	 * */
	public String getPath(){
		return this.file.getAbsolutePath();
	}
	
	/**
	 * flag return. 0 = dir / 1 = file
	 * @return
	 */
	public int getFOI(){
		return this.foi.getFlag();
	}
}
