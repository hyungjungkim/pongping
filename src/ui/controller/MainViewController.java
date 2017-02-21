package ui.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * MainView 이벤트핸들러
 * 
 * @author Administrator
 *
 */
public class MainViewController implements Initializable {

	private FileClient fileClient;

	/**
	 * 좌측 부분
	 */
	@FXML
	private Button btnLogOut;
	@FXML
	private Button btnDevInfo;
	@FXML
	private Label labelUserName;

	/**
	 * 우측 상단 부분
	 */
	@FXML
	private TextField textFieldSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Label labelCurrentPath;

	@FXML
	private Button btnCreateFolder;
	@FXML
	private Button btnFileUpload;
	@FXML
	private Button btnFileDownload;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnChangeName;

	@FXML
	private Alert alertDevInfo;

	private List<DirFile> dirFile;
	private String userId = "userId";
	private String searchName = "serachName";
	private String currentPath = "/a/b/c";

	/**
	 * 우측 하단 부분
	 */

	// @FXML private TableView<Posting> postingListTableView;

	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//

		//초기 로그인 시 받는 리스트
		dirFile = fileClient.ShowList(userId,"0");

	}

	/**
	 * 생성자
	 */

	public MainViewController() {
		//
		// socket()에 IP 주소 넣기
		Socket soc = new Socket();
		fileClient = new FileClientLogic(soc);

		alertDevInfo = new Alert(AlertType.INFORMATION);

		// To-do
		dirFile = new ArrayList<DirFile>();
	}

	/**
	 * 좌측 부분
	 */

	// 로그아웃 버튼 눌렀을때
	public void handleLogOut(ActionEvent e) {
		//
		// 화면 이동
	}

	// 개발자 정보 버튼 눌렀을때
	public void btnDevInfo(ActionEvent e) {
		//
		// alert
		alertDevInfo.setContentText("asdasdasd");
		alertDevInfo.show();
	}

	/**
	 * 우측 상단 부분
	 */

	// 검색 버튼 눌렀을때
	public void handleSearch(ActionEvent e) {
		// Todo
		dirFile = fileClient.FileSearch(userId, searchName);

		DisplayList();
	}

	// 폴더 생성 버튼 눌렀을때
	public void handleCreateFolder(ActionEvent e) {
		// Todo
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("CreateFolderName");
		dialog.setHeaderText("Set a forder Name");
		dialog.setContentText("Please enter folder name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Folder name: " + result.get());
		}

		// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(name -> System.out.println("Folder name: " + name));

		dirFile = fileClient.DirectoryCreate(userId, currentPath);

		DisplayList();
	}

	// 파일 업로드 버튼 눌렀을때
	public void handleFileUpload(ActionEvent e) {
		//
		try {
			dirFile = fileClient.FileUpload(userId, "localPath");
		} catch (IOException d) {
			d.printStackTrace();
		}
	}

	// 파일 다운로드 버튼 눌렀을때
	public void handleFileDownload(ActionEvent e) {
		//
		boolean isFileDownload = false;
		try {
			isFileDownload = fileClient.FileDownload(userId, "localPath");
		} catch (IOException f) {
			f.printStackTrace();
		}
		// 파일 다운이 가능하면
		if (isFileDownload) {
			System.out.println("파일 다운로드 완료");
		} else {
			System.out.println("파일 다운로드 실패");
		}
	}

	// 삭제 버튼 눌렀을때
	public void handleDelete(ActionEvent e) {
		//
		dirFile = fileClient.DirectoryRemove(userId, currentPath);

		DisplayList();
	}

	// 이름 변경 버튼 눌렀을때
	public void handleChangeName(ActionEvent e) {
		//
		dirFile = fileClient.ChangeName(userId, currentPath, "newPath");
		
		DisplayList();
	}

	/**
	 * 내부 메소드
	 */

	// 현재 경로 폴더/파일 보여주기
	public void DisplayList() {
		//

	}

	// 중복인지 검사
	public boolean IsExist() {
		//
		return false;
	}

	// 상위 폴더로 이동
	public boolean Back() {
		//
		return false;
	}

}
