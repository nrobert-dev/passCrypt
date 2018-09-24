package model;

import java.util.ArrayList;
import java.util.List;

public class UserInfo implements java.io.Serializable {

	private String username;
	private String password;
	
	public List<UserPassEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<UserPassEntry> entries) {
		this.entries = entries;
	}

	private List<UserPassEntry> entries;
	
	public UserInfo(){
		username="";
		password="";
		
	}
	
	public UserInfo(String username,String password,List<UserPassEntry> list){
		this.username = username;
		this.password = password;	
		
		entries = new ArrayList<UserPassEntry>();
		entries.addAll(list);
	}
	public UserInfo(String username,String password){
		this.username = username;
		this.password = password;	
		
		entries = new ArrayList<UserPassEntry>();
		
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEmpty(){
		if(username.equals("") || password.equals("")) return true;
		return false;
	}
	
	
}
