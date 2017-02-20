package ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * MainView 이벤트핸들러
 * @author Administrator
 *
 */
public class MainViewController implements Initializable{

	//
	@FXML //FXML에서 id에 해당하는 필드를 찾아서 직접 set을 해줘 
	private TextField txtMessage; //Id랑 동일해야 함
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
//	
	
}
