package view;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Hasher;
import model.Main;
import model.UserInfo;
import model.UserInfoHandling;

public class LoginWindowController {
	
	private Main main;
	
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	Button signUp;
	
	@FXML
	private void signUpButton() throws IOException{
		if(main!=null){
		  main.initSignUpWindow();
		}
	}
	
	@FXML
	private void signInButton() throws IOException{
		boolean status = false;
		File f = new File("crdInfo.ser");
		if(f.exists() && !f.isDirectory()) {
		UserInfo loadedUser = UserInfoHandling.getUserInfo("crdInfo.ser");
		
		if(Hasher.HashIt(usernameField.getText()).equals(loadedUser.getUsername())&&
				Hasher.HashIt(passwordField.getText()).equals(loadedUser.getPassword()))
		{
			status = true;
			main.initEntiresLayoutWindow();
		}
		
		System.out.print("The login was :" + status + "\n");
		}
		
	}
	
	public void setUpMain(Main main){
		this.main = main;
	}

	
}
