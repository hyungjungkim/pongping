package db.domain;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class ListInfor implements Serializable{
	//
	/*
	 * Class ListInfor for returning list
	 * Serializable
	 */
	
	//Version
	private static final long serialVersionUID = 132111111015102313L;
	
	private List<File> listInfor;
	
	//Constructor
	public ListInfor(){
		//
	}
	public List<File> getListInfor() {
		return listInfor;
	}
	public void setListInfor(List<File> listInfor) {
		this.listInfor = listInfor;
	}
	
}
