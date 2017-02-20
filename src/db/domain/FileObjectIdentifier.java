package db;

public enum FileObjectIdentifier {
	Folder(0), File(1);
	
	private int flag;
	
	private FileObjectIdentifier(int flag){
		this.flag =flag;
	}
	
	public int getFlag(){
		return flag;
	}
}
