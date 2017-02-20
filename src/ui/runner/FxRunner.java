package ui.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxRunner extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//
		//1. FXML������ �̿��ؼ� Parent ��ü ����
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/mainfx.fxml"));
		Parent root = loader.load();

		//2. Scene ����(parent�� �ʿ���)
		Scene scene = new Scene(root);
		
		//3. Stage�� Scene �¾�
		primaryStage.setScene(scene);
		primaryStage.setTitle("File Server Project");
		primaryStage.show();
	
		/*
		//////////////////////////////////////////////////////////////////////////////////////
		//1��° ���, �̺�Ʈ �ڵ鷯 ���(ģ���� �ڹ� ����)
		Button button = new Button();
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Button clicked");
			}
		});
		*/
	}
	
	public static void main(String[] args){
		//
		launch(args);
		}
}
