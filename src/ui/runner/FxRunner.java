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
		//1. FXML파일을 이용해서 Parent 객체 생성
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/mainfx.fxml"));
		Parent root = loader.load();

		//2. Scene 생성(parent가 필요함)
		Scene scene = new Scene(root);
		
		//3. Stage에 Scene 셋업
		primaryStage.setScene(scene);
		primaryStage.setTitle("File Server Project");
		primaryStage.show();
	
		/*
		//////////////////////////////////////////////////////////////////////////////////////
		//1번째 방법, 이벤트 핸들러 등록(친숙한 자바 문법)
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
