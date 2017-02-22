package db.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import db.domain.User;

public class UserStore {
	private User user;
	private List<User> userList;
	private static final File userInfo = new File("C:/FileServer/UserInfo.txt");
	
	public UserStore(){
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(userInfo));
			String[] pars = (br.readLine()).split(","); 	//userid , user password, name
			
			user = new User(pars[0],pars[1],pars[2]);
			userList.add(user);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}
	}
	
	public User logOn(String id, String password){
		return null;
	}
	
	public void logOff(User user){
		
	}
	
	public void changePW(String password, String newPassword){
		
	}
}
