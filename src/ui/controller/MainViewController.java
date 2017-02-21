package ui.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

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
import network.server.TCPReactor;

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

	@FXML
	private Alert alertChangeName;

	private List<DirFile> dirFile = new ArrayList<DirFile>();
	private String userId = "userId";
	private String searchName = "serachName";
	private String currentPath;
	private static final String localPath = "C:\\";
	
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

		alertDevInfo = new Alert(AlertType.INFORMATION);

		// 초기 로그인 시 받는 리스트
		try {
			dirFile = fileClient.ShowList(userId, "0");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPath = userId;
		DisplayList();
	}

	/**
	 * 생성자
	 */

	public MainViewController() {
		//
		// 소켓 생성 후 socket()에 IP 주소 넣기
		try {
			TCPReactor tcpReactor = new TCPReactor("123.456.789.0", 9999);
			Socket soc = tcpReactor.getClient();
			fileClient = new FileClientLogic(soc);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 좌측 부분
	 */

	// 로그아웃 버튼 눌렀을때
	public void handleLogOut(ActionEvent e) {
		//
		// 화면 이동
	}

	/**
	 * Dev Info
	 * 
	 * @param e
	 */
	public void btnDevInfo(ActionEvent e) {
		//
		// alert
		alertDevInfo.setContentText("SKCC 4조");
		alertDevInfo.show();
	}

	/**
	 * 우측 상단 부분
	 */

	/**
	 * 파일/폴더 검색
	 * 
	 * @param e
	 */
	//완료
	public void handleSearch(ActionEvent e) {
		try {
			dirFile = fileClient.FileSearch(userId, searchName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DisplayList();
	}

	/**
	 * 폴더 생성
	 * @param e
	 */
	// 완료
	public void handleCreateFolder(ActionEvent e) {
		
		String entered = "none.";
		TextInputDialog dialog = new TextInputDialog("hi");
		dialog.setTitle("hi");
		dialog.setHeaderText("Change Name");

		Optional<String> result = dialog.showAndWait();
		String folderNameWithPath ="";
		if (result.isPresent()) {

			folderNameWithPath = currentPath + "/" + result.get();
		}
		
		// 중복 체크 후 이름 변경
		if (IsExist(entered)) {

			System.out.println("응 폴더 존재해");
		} else {
			try {
				dirFile = fileClient.DirectoryCreate(userId, folderNameWithPath);
				DisplayList();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
	}

	/**
	 * 파일 업로드
	 * 
	 * @param e
	 */

	public void handleFileUpload(ActionEvent e) {
		//
		try {
			//Todo
			String localFilePath = selectedFile.getPath();
			
			String entered = "none.";
			TextInputDialog dialog = new TextInputDialog("hi");
			dialog.setTitle("FileUpload");
			dialog.setHeaderText("Set Upload File Name :");

			Optional<String> result = dialog.showAndWait();
			String fileNameWithPath ="";
			if (result.isPresent()) {

				fileNameWithPath = currentPath + "/" + result.get();
			}
			
			dirFile = fileClient.FileUpload(userId, localFilePath, fileNameWithPath);
		} catch (IOException d) {
			d.printStackTrace();
		}
		DisplayList();
	}

	/**
	 * 파일 다운로드
	 * 
	 * @param e
	 */
	// 완료
	public void handleFileDownload(ActionEvent e) {

		boolean isFileDownload = false;
		try {
			String localFilePath = selectedFile.getPath();
			isFileDownload = fileClient.FileDownload(userId, localPath, localFilePath);
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

	/**
	 * 파일/폴더 삭제
	 * 
	 * @param e
	 */

	public void handleDelete(ActionEvent e) {
		//
		try {
			//파일 이름 가져오기
			String fileNameWithPath = get()+"/"+currentPath;
			dirFile = fileClient.DirectoryRemove(userId, fileNameWithPath);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DisplayList();
	}

	/**
	 * 파일/폴더 이름 변경
	 * 
	 * @param e
	 */

	public void handleChangeName(ActionEvent e) {

		String entered = "none.";
		TextInputDialog dialog = new TextInputDialog("hi");
		dialog.setTitle("hi");
		dialog.setHeaderText("Change Name");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {

			entered = result.get();
		}

		// 중복 체크 후 이름 변경
		if (IsExist(entered)) {

			System.out.println("응 파일 존재해");
		} else {
			String newPath = currentPath + "/" + entered;
			try {
				dirFile = fileClient.ChangeName(userId, currentPath, newPath);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DisplayList();
		}
	
	}

	/**
	 * 내부 메소드
	 */

	/**
	 * UI 갱신
	 */
	// 현재 경로 폴더/파일 보여주기
	public void DisplayList() {
		//

	}

	/**
	 * 중복 검사
	 * 
	 * @return
	 */
	//Todo
	public boolean IsExist(String name) {
		// 만약 폴더 혹은 파일 이름이랑 같으면 showlist 반환값의 끄트머리랑 비교
		
		return false;
	}

	/**
	 * 상위 폴더 이동
	 */
	// 완료
	public void Back() {

		int lastIndex = currentPath.lastIndexOf("/");
		String renewPath = currentPath.substring(0, lastIndex);

		try {
			dirFile = fileClient.ShowList(userId, renewPath);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DisplayList();
	}

	/**
	 * 하위 폴더 이동
	 */
	public void Forward(String currentPath, String pathToGo) {

		String renewPath = currentPath + "/" + pathToGo;

		try {
			dirFile = fileClient.ShowList(userId, renewPath);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DisplayList();
	}
	
	/**
	 * 검색 후 다운로드
	 */
	public void SearchedFileDownload(){
		
	}
}
