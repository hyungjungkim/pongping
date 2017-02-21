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
 * MainView �̺�Ʈ�ڵ鷯
 * 
 * @author Administrator
 *
 */
public class MainViewController implements Initializable {

	private FileClient fileClient;

	/**
	 * ���� �κ�
	 */
	@FXML
	private Button btnLogOut;
	@FXML
	private Button btnDevInfo;
	@FXML
	private Label labelUserName;

	/**
	 * ���� ��� �κ�
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

	private List<DirFile> dirFile = new ArrayList<DirFile>();
	private String userId = "userId";
	private String searchName = "serachName";
	private String currentPath = "/a/b/c";

	/**
	 * ���� �ϴ� �κ�
	 */

	// @FXML private TableView<Posting> postingListTableView;

	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;
	// @FXML private TableColumn<Posting, String> postingId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		alertDevInfo = new Alert(AlertType.INFORMATION);
		// �ʱ� �α��� �� �޴� ����Ʈ
		dirFile = fileClient.ShowList(userId, "0");
		currentPath = userId;
		DisplayList();
	}

	/**
	 * ������
	 */

	public MainViewController() {
		//
		// ���� ���� �� socket()�� IP �ּ� �ֱ�
		try {
			TCPReactor tcpReactor = new TCPReactor("123.456.789.0", 9999);
			Socket soc = tcpReactor.getClient();
			fileClient = new FileClientLogic(soc);
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

	/**
	 * ���� �κ�
	 */

	// �α׾ƿ� ��ư ��������
	public void handleLogOut(ActionEvent e) {
		//
		// ȭ�� �̵�
	}

	/**
	 * Dev Info
	 * 
	 * @param e
	 */
	public void btnDevInfo(ActionEvent e) {
		//
		// alert
		alertDevInfo.setContentText("SKCC 4��");
		alertDevInfo.show();
	}

	/**
	 * ���� ��� �κ�
	 */

	/**
	 * ����/���� �˻�
	 * 
	 * @param e
	 */
	public void handleSearch(ActionEvent e) {
		dirFile = fileClient.FileSearch(userId, searchName);
		DisplayList();
	}

	// ���� ���� ��ư ��������
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

	/**
	 * ���� ���ε�
	 * 
	 * @param e
	 */

	public void handleFileUpload(ActionEvent e) {
		//
		try {
			dirFile = fileClient.FileUpload(userId, "localPath");
		} catch (IOException d) {
			d.printStackTrace();
		}
		DisplayList();
	}

	/**
	 * ���� �ٿ�ε�
	 * 
	 * @param e
	 */
	//�Ϸ�
	public void handleFileDownload(ActionEvent e) {

		boolean isFileDownload = false;
		try {
			isFileDownload = fileClient.FileDownload(userId, "localPath");
		} catch (IOException f) {
			f.printStackTrace();
		}
		// ���� �ٿ��� �����ϸ�
		if (isFileDownload) {

			System.out.println("���� �ٿ�ε� �Ϸ�");
		} else {
			System.out.println("���� �ٿ�ε� ����");
		}
	}

	/**
	 * ����/���� ����
	 * 
	 * @param e
	 */
	
	public void handleDelete(ActionEvent e) {
		//
		dirFile = fileClient.DirectoryRemove(userId, currentPath);

		DisplayList();
	}

	/**
	 * ����/���� �̸� ����
	 * 
	 * @param e
	 */

	public void handleChangeName(ActionEvent e) {
		//
		dirFile = fileClient.ChangeName(userId, currentPath, "newPath");

		DisplayList();
	}

	/**
	 * ���� �޼ҵ�
	 */

	/**
	 * UI ����
	 */
	// ���� ��� ����/���� �����ֱ�
	public void DisplayList() {
		//

	}

	/**
	 * �ߺ� �˻�
	 * 
	 * @return
	 */
	public boolean IsExist() {
		//
		return false;
	}

	/**
	 * ���� ���� �̵�
	 */
	// �Ϸ�
	public void Back() {

		int lastIndex = currentPath.lastIndexOf("/");
		String renewPath = currentPath.substring(0, lastIndex);

		dirFile = fileClient.ShowList(userId, renewPath);
		DisplayList();
	}
}
