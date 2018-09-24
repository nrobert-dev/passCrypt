package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

public class UserInfoHandling {

	
	private static void encryptAll(String userHash,List<UserPassEntry> list){
		String subString = userHash.substring(0, 16);
		byte[] key = subString.getBytes();
		
		for(int i=0;i<list.size();i++){
			try {
				list.get(i).encryptPass(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void decryptAll(String userHash,List<UserPassEntry> list){
		String subString = userHash.substring(0, 16);
		byte[] key = subString.getBytes();
		
		for(int i=0;i<list.size();i++){
			try {
				list.get(i).decryptPass(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void updateUserInfo(String userHash,String passHash,List<UserPassEntry> list,String path){
		UserInfo userInfoToSave = new UserInfo(userHash,passHash,list);
		encryptAll(userHash,list);
		try{
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(userInfoToSave);
			objectOut.close();
			fileOut.close();
			System.out.println("Data saved succes");
			
		}
		catch(IOException i) {
	         i.printStackTrace();
		}
		
		
	}
	public static int saveUserInfo(String userHash,String passHash,String path){
		UserInfo userInfoToSave = new UserInfo(userHash,passHash);
		try{
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(userInfoToSave);
			objectOut.close();
			fileOut.close();
			System.out.println("Data saved succes");
			return 0;
		}
		catch(IOException i) {
	         i.printStackTrace();
	    }
		return -1;
		
	}
	
	public static UserInfo getUserInfo(String path)
	{
		UserInfo loadedUser = new UserInfo();
		if(!path.equals("")){
			try{
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				loadedUser = (UserInfo) objectIn.readObject();
				decryptAll(loadedUser.getUsername(),loadedUser.getEntries());
				objectIn.close();
				fileIn.close();		
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
		return loadedUser;
	} 
	
}
