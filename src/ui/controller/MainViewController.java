package ui.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.domain.DirFile;
import fileprocessor.client.FileClient;
import fileprocessor.client.FileClientLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * MainView 이벤트핸들러
 * @author Administrator
 *
 */
public class MainViewController implements Initializable{
	
	
	private FileClient fileClient;
	
	
	/**
	 * 좌측 부분
	 */
	@FXML private Button btnLogOut;
	@FXML private Button btnDevInfo;
	@FXML private Label labelUserName;
	
	/**
	 * 우측 상단 부분
	 */
	@FXML private TextField textFieldSearch;
	@FXML private Button btnSearch;
	@FXML private Label labelCurrentPath;
	
	@FXML private Button btnCreateFolder;
	@FXML private Button btnFileUpload;
	@FXML private Button btnFileDownload;
	@FXML private Button btnDelete;
	@FXML private Button btnChangeName;
	
	/**
	 * 우측 하단 부분
	 */
	
	//@FXML private TableView<Posting> postingListTableView;
	
	//@FXML private TableColumn<Posting, String> postingId;
	//@FXML private TableColumn<Posting, String> postingId;
	//@FXML private TableColumn<Posting, String> postingId;
	//@FXML private TableColumn<Posting, String> postingId;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 
		
	
		
	}
	
	/**
	 * 생성자
	 */
	
	public MainViewController() {
		//
		fileClient = new FileClientLogic();
		
		
	}
	
	
	/**
	 * 좌측 부분
	 */
	
	//로그아웃 버튼 눌렀을때
	public void handleLogOut(ActionEvent e) {
		// 
		//화면 이동
	}
	
	//개발자 정보 버튼 눌렀을때
	public void btnDevInfo(ActionEvent e) {
		// 
		//alert
	}
	
	/**
	 * 우측 상단 부분
	 */
	
	//검색 버튼 눌렀을때
	public void handleSearch(ActionEvent e) {
		// 
		
	}
	
	//폴더 생성 버튼 눌렀을때
	public void handleCreateFolder(ActionEvent e) {
		// 
		
	}
	
	//파일 업로드 버튼 눌렀을때
	public void handleFileUpload(ActionEvent e) {
		// 
		
	}
	
	//파일 다운로드 버튼 눌렀을때
	public void handleFileDownload(ActionEvent e) {
		// 
		
	}
	
	//삭제 버튼 눌렀을때
	public void handleDelete(ActionEvent e) {
		// 
		
	}
	
	//이름 변경 버튼 눌렀을때
	public void handleChangeName(ActionEvent e) {
		// 
		
	}
	
	/**
	 * 내부 메소드
	 */
	
	//현재 경로 폴더/파일 보여주기
	public void ShowList(DirFile dirFile) {
		//
		
	}
	
	//중복인지 검사
	public boolean IsExist() {
		//
		return false;
	}
	
	//상위 폴더로 이동
	public boolean Back() {
		//
		return false;
	}
	
	
}
