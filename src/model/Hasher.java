package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

	public static String HashIt(String input) {
			
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		byte[] inputBytes = input.getBytes(); //get bytes array from message.		 
		byte[] hashBytes = digest.digest(inputBytes);

		
		//convert into hex
        String hexStr = "";
        for (int i = 0; i < hashBytes.length; i++) {
            hexStr +=  Integer.toString( ( hashBytes[i] & 0xff ) + 0x100, 16).substring(1);
        }
        return hexStr;	
		
	
	}
		
}
