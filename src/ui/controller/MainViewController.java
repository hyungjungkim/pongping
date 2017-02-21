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

	@FXML
	private Alert alertChangeName;

	private List<DirFile> dirFile = new ArrayList<DirFile>();
	private String userId = "userId";
	private String searchName = "serachName";
	private String currentPath;
	private static final String localPath = "C:\\";
	
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
	//�Ϸ�
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
	 * ���� ����
	 * @param e
	 */
	// �Ϸ�
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
		
		// �ߺ� üũ �� �̸� ����
		if (IsExist(entered)) {

			System.out.println("�� ���� ������");
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
	 * ���� ���ε�
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
	 * ���� �ٿ�ε�
	 * 
	 * @param e
	 */
	// �Ϸ�
	public void handleFileDownload(ActionEvent e) {

		boolean isFileDownload = false;
		try {
			String localFilePath = selectedFile.getPath();
			isFileDownload = fileClient.FileDownload(userId, localPath, localFilePath);
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
		try {
			//���� �̸� ��������
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
	 * ����/���� �̸� ����
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

		// �ߺ� üũ �� �̸� ����
		if (IsExist(entered)) {

			System.out.println("�� ���� ������");
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
	//Todo
	public boolean IsExist(String name) {
		// ���� ���� Ȥ�� ���� �̸��̶� ������ showlist ��ȯ���� ��Ʈ�Ӹ��� ��
		
		return false;
	}

	/**
	 * ���� ���� �̵�
	 */
	// �Ϸ�
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
	 * ���� ���� �̵�
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
	 * �˻� �� �ٿ�ε�
	 */
	public void SearchedFileDownload(){
		
	}
}
