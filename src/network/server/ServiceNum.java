package network.server;

public enum ServiceNum {

	UPLOAD("Upload"),
	DOWNLOAD("Download"),
	MKDIR("MkDir"),
	RMVDIR("RmvDir"),
	RMFILE("RmvFile"),
	CNGFILENAME("CngFileName"),
	CNGDIRNAME("CngDirName"),
	SEARCH("Search"),
	SHOWLIST("ShowList");
	
	public String service;
	
	private ServiceNum(String service){
		
		this.service = service;
		
	}
	
}
