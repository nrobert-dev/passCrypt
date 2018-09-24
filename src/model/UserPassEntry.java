package model;

import java.security.Key;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UserPassEntry implements java.io.Serializable {

	
	
	private String description;
	private String username;
	private String password;
	
	public UserPassEntry(){
		description="";
		username = "";
		password = "";
	}
	
	public UserPassEntry(String username,String password,String description){
		this.username = username;
		this.password = password;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public String toString(){
		return "DESCRIPTION :" + this.getDescription() + "| USERNAME :" + this.getUsername() + "| PASSWORD :"+this.getPassword();
	}
	
    private static final String ALGO = "AES";
    
    public void decryptPass(byte[] keyValue) throws Exception {
       Key key = generateKey(keyValue);
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.DECRYPT_MODE, key);
       
       byte[] decordedValue = Base64.getDecoder().decode(this.password);
       byte[] decValue = c.doFinal(decordedValue);
       this.password = new String(decValue);  
       
       decordedValue = Base64.getDecoder().decode(this.username);
       decValue = c.doFinal(decordedValue);
       this.username = new String(decValue);
       
   }
	
	public void encryptPass(byte[] keyValue) throws Exception{
		Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] encPass = c.doFinal(password.getBytes());      
        String encryptedValue = Base64.getEncoder().encodeToString(encPass);
        
        this.password = encryptedValue;
        
        encPass = c.doFinal(username.getBytes());      
        encryptedValue = Base64.getEncoder().encodeToString(encPass);
        this.username = encryptedValue;
           
	}
	
	private Key generateKey(byte[] keyValue) throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
	
}
