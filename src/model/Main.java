package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.EntriesListController;
import view.LoginWindowController;
import view.SignUpController;

public class Main extends Application{

	
	private Stage primaryStage;
	private BorderPane mainWindowLayout;
	
	public static void main(String[] args) {
		
		
		
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		initWindowWrap();
		initLoginWindow();
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	
	private void initWindowWrap() throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FirstWindowWrap.fxml"));
		
		mainWindowLayout = loader.load();
		Scene mainWindowScene = new Scene(mainWindowLayout);
		primaryStage.setScene(mainWindowScene);
		
		primaryStage.setMaxHeight(400);
		primaryStage.setMaxWidth(600);
		primaryStage.show();	
	}
	
	private void initLoginWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginWindowLayout.fxml"));
		
		AnchorPane loginWindowLayout = loader.load();
		mainWindowLayout.setCenter(loginWindowLayout);
		
		LoginWindowController loginWindowController = loader.getController();
		loginWindowController.setUpMain(this);
	}
	
	public void initSignUpWindow() throws IOException{
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/SignUpLayout.fxml"));
		
		AnchorPane signUpLayout = (AnchorPane) loader.load();
		
		Stage signUpStage = new Stage();
		signUpStage.setTitle("Create Account");
		signUpStage.initModality(Modality.WINDOW_MODAL);
		signUpStage.initOwner(primaryStage);
		
		Scene signUpScene = new Scene(signUpLayout);
		signUpStage.setScene(signUpScene);
		
		SignUpController signUpController = loader.getController();
		signUpController.setUpMainStage(signUpStage);
		
		signUpStage.showAndWait();
		
	}
	
	public void initEntiresLayoutWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/EntriesListLayout.fxml"));
		
		AnchorPane entriesLayout = (AnchorPane) loader.load();
	
		Stage entriesStage = new Stage();
		entriesStage.setTitle("Entries");
		
		Scene entriesScene = new Scene(entriesLayout);
		entriesStage.setScene(entriesScene);
		
		EntriesListController controller = loader.getController();
		controller.setUpMain(this);
        controller.setUpStage(entriesStage);
		
		entriesStage.show();
		primaryStage.close();
		
		
	}
	
	

	

}
