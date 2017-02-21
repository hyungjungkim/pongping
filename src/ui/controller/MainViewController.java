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

	private List<DirFile> dirFile;
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
		//

		//�ʱ� �α��� �� �޴� ����Ʈ
		dirFile = fileClient.ShowList(userId,"0");

	}

	/**
	 * ������
	 */

	public MainViewController() {
		//
		// socket()�� IP �ּ� �ֱ�
		Socket soc = new Socket();
		fileClient = new FileClientLogic(soc);

		alertDevInfo = new Alert(AlertType.INFORMATION);

		// To-do
		dirFile = new ArrayList<DirFile>();
	}

	/**
	 * ���� �κ�
	 */

	// �α׾ƿ� ��ư ��������
	public void handleLogOut(ActionEvent e) {
		//
		// ȭ�� �̵�
	}

	// ������ ���� ��ư ��������
	public void btnDevInfo(ActionEvent e) {
		//
		// alert
		alertDevInfo.setContentText("asdasdasd");
		alertDevInfo.show();
	}

	/**
	 * ���� ��� �κ�
	 */

	// �˻� ��ư ��������
	public void handleSearch(ActionEvent e) {
		// Todo
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

	// ���� ���ε� ��ư ��������
	public void handleFileUpload(ActionEvent e) {
		//
		try {
			dirFile = fileClient.FileUpload(userId, "localPath");
		} catch (IOException d) {
			d.printStackTrace();
		}
	}

	// ���� �ٿ�ε� ��ư ��������
	public void handleFileDownload(ActionEvent e) {
		//
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

	// ���� ��ư ��������
	public void handleDelete(ActionEvent e) {
		//
		dirFile = fileClient.DirectoryRemove(userId, currentPath);

		DisplayList();
	}

	// �̸� ���� ��ư ��������
	public void handleChangeName(ActionEvent e) {
		//
		dirFile = fileClient.ChangeName(userId, currentPath, "newPath");
		
		DisplayList();
	}

	/**
	 * ���� �޼ҵ�
	 */

	// ���� ��� ����/���� �����ֱ�
	public void DisplayList() {
		//

	}

	// �ߺ����� �˻�
	public boolean IsExist() {
		//
		return false;
	}

	// ���� ������ �̵�
	public boolean Back() {
		//
		return false;
	}

}
