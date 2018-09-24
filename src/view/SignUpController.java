package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Hasher;
import model.UserInfoHandling;

public class SignUpController {

	
	private Stage mainStage;
	
	public void setUpMainStage(Stage mainStage)
	{
		this.mainStage = mainStage;
	}
	@FXML
	TextField usernameCreate;
	
	@FXML
	PasswordField passwordCreate;
	
	@FXML
	Label creationStatus;
	
	@FXML
	private void cancelButton(){
		if(mainStage!=null){
			mainStage.close();
		}
	}
	
	@FXML 
	private void okButton(){
		String usrHash = Hasher.HashIt(usernameCreate.getText());
		String passHash = Hasher.HashIt(passwordCreate.getText());
		
		int status = UserInfoHandling.saveUserInfo(usrHash, passHash, "crdInfo.ser");
		if(status==0){
			creationStatus.setText("The account was created!");
		}
		else{
			creationStatus.setText("Error creating account!");
		}
	}
	
}
