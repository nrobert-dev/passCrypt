package view;

import java.io.IOException;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Main;
import model.UserInfo;
import model.UserInfoHandling;
import model.UserPassEntry;

public class EntriesListController {

	
	private Main main;
	private Stage stage;
	private UserInfo userInfo;
	protected ListProperty<UserPassEntry> listProperty = new SimpleListProperty<>();
	
	
	public void setUpStage(Stage stage){
		this.stage = stage;
	}
	
	public void setUpMain(Main main){
		this.main = main;
	}
	@FXML
	ListView entriesList;
	
	@FXML
	TextField usernameField;
	
	@FXML 
	TextField passwordField;
	
	@FXML
	TextField descriptionField;
	
	public void initialize(){

		userInfo = UserInfoHandling.getUserInfo("crdInfo.ser");
		listProperty.set(FXCollections.observableArrayList(userInfo.getEntries()));
		
		entriesList.itemsProperty().bind(listProperty);	
	}
	
	@FXML
	private void addEntry() throws IOException{
		
		if(!usernameField.getText().equals("") && 
				!passwordField.getText().equals("") && 
				!descriptionField.getText().equals("")){
			
			UserPassEntry entryToAdd = new UserPassEntry(usernameField.getText(),passwordField.getText(),
					descriptionField.getText());
			usernameField.setText("");
			passwordField.setText("");
			descriptionField.setText("");
			
			
		userInfo.getEntries().add(entryToAdd);
		listProperty.add(entryToAdd);
		}

	}
	
	@FXML
	private void saveButton() throws IOException{
		UserInfoHandling.updateUserInfo(userInfo.getUsername(), userInfo.getPassword(), userInfo.getEntries(),"crdInfo.ser");
		stage.close();
	}
	
	@FXML
	private void deleteButton() throws IOException{
		int index = entriesList.getSelectionModel().getSelectedIndex();
		if(index !=-1){
			entriesList.getItems().remove(index);
			userInfo.getEntries().remove(index);
		}
	}
	
	
	
}
