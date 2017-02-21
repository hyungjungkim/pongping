package db.domain;

import java.util.Date;

public class DirFile {
	/** file or folder index*/
	private int index;
	/** file or folder name*/
	private String fileName;
	/** User Id*/
	private String userId;
	/** upper direcotry's index root=0 else >0*/
	private int parentDirIdx;
	/** Last Modified Date*/
	private Date modifiedDate;
	/** folder = 0 , file =1*/
	private FileObjectIdentifier flag;
	
	public DirFile(){
		
	}
	
	public DirFile(int index, String fileName, String userId, int parentDirIdx, Date modifiedDate,
			FileObjectIdentifier flag) {
		super();
		this.index = index;
		this.fileName = fileName;
		this.userId = userId;
		this.parentDirIdx = parentDirIdx;
		this.modifiedDate = modifiedDate;
		this.flag = flag;
	}



	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getParentDirIdx() {
		return parentDirIdx;
	}

	public void setParentDirIdx(int parentDirIdx) {
		this.parentDirIdx = parentDirIdx;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public FileObjectIdentifier getFlag() {
		return flag;
	}

	public void setFlag(FileObjectIdentifier flag) {
		this.flag = flag;
	}

	public void print(){
		System.out.println("DirFile [index=" + index + ", fileName=" + fileName + ", userId=" + userId + ", parentDirIdx="
				+ parentDirIdx + ", modifiedDate=" + modifiedDate + ", flag=" + flag + ", getIndex()=" + getIndex()
				+ ", getFileName()=" + getFileName() + ", getUserId()=" + getUserId() + ", getParentDirIdx()="
				+ getParentDirIdx() + ", getModifiedDate()=" + getModifiedDate() + ", getFlag()=" + getFlag() + "]");
	}
	
	
}
