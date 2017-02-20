package db.domain;

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
		children = new ArrayList<>();
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
	 * �쁽�옱 寃쎈줈�궡�뿉, �븯�쐞 �뤃�뜑 諛� �뙆�씪 ���옣 由ъ뒪�듃
	 * @param dirFile
	 */
	public void addChild(DirFile dirFile){
		this.children.add(dirFile);
	}
	
	/**
	 * �쁽�옱 寃쎈줈�궡�뿉 �븯�쐞 �뤃�뜑&�뙆�씪�뱾 由ы꽩 .
	 */
	public ArrayList<DirFile> getChildren(){
		return this.children;
	}

	/**
	 * �쁽�옱 寃쎈줈�궡�뿉,name怨� 媛숈� �븯�쐞 �뤃�뜑 �삉�뒗 �뙆�씪 由ы꽩 .
	 * @param name
	 */
	public DirFile getChild(String name){
		if(this.children != null){
			for(DirFile d : children)
				if(d.getName().equals(name))
					return d;
		}

        return null;
	}
	
	/**
	 * �쁽�옱 寃쎈줈 諛섑솚 
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
