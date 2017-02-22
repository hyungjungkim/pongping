package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

	@FXML
	private TextField textFieldUserId;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField textFieldUserPw;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		

	}
	
	public void handleLogin(ActionEvent e) {
		// 
		try {
			
			String userId = textFieldUserId.getText();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/mainfx.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			MainViewController mainViewController = loader.getController();
			
			mainViewController.setUserId(userId);
			
			Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("나무보드");
			primaryStage.show();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
}
