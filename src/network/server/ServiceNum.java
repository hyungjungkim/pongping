package network.server;

public enum ServiceNum {

	UPLOAD("업로드"),
	DOWNLOAD("다운로드"),
	MKDIR("디렉토리생성"),
	RMVDIR("디렉토리삭제"),
	CNGFILENAME("파일이름변경"),
	CNGDIRNAME("디렉토리이름변경"),
	SEARCH("파일검색");
	
	public String service;
	
	private ServiceNum(String service){
		
		this.service = service;
		
	}
	
}
