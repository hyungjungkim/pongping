package network.server;

public enum ServiceNum {

	UPLOAD("���ε�"),
	DOWNLOAD("�ٿ�ε�"),
	MKDIR("���丮����"),
	RMVDIR("���丮����"),
	CNGFILENAME("�����̸�����"),
	CNGDIRNAME("���丮�̸�����"),
	SEARCH("���ϰ˻�");
	
	public String service;
	
	private ServiceNum(String service){
		
		this.service = service;
		
	}
	
}
