package network.server;

public enum ServiceNum {

	UPLOAD("Upload"),
	DOWNLOAD("Download"),
	MKDIR("MkDir"),
	RMVDIR("RmvDir"),
	CNGFILENAME("CngFileName"),
	CNGDIRNAME("CngDirName"),
	SEARCH("Search");
	
	public String service;
	
	private ServiceNum(String service){
		
		this.service = service;
		
	}
	
}
