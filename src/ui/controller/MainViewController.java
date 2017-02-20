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
 * MainView �̺�Ʈ�ڵ鷯
 * @author Administrator
 *
 */
public class MainViewController implements Initializable{
	
	
	private FileClient fileClient;
	
	
	/**
	 * ���� �κ�
	 */
	@FXML private Button btnLogOut;
	@FXML private Button btnDevInfo;
	@FXML private Label labelUserName;
	
	/**
	 * ���� ��� �κ�
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
	 * ���� �ϴ� �κ�
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
	 * ������
	 */
	
	public MainViewController() {
		//
		fileClient = new FileClientLogic();
		
		
	}
	
	
	/**
	 * ���� �κ�
	 */
	
	//�α׾ƿ� ��ư ��������
	public void handleLogOut(ActionEvent e) {
		// 
		//ȭ�� �̵�
	}
	
	//������ ���� ��ư ��������
	public void btnDevInfo(ActionEvent e) {
		// 
		//alert
	}
	
	/**
	 * ���� ��� �κ�
	 */
	
	//�˻� ��ư ��������
	public void handleSearch(ActionEvent e) {
		// 
		
	}
	
	//���� ���� ��ư ��������
	public void handleCreateFolder(ActionEvent e) {
		// 
		
	}
	
	//���� ���ε� ��ư ��������
	public void handleFileUpload(ActionEvent e) {
		// 
		
	}
	
	//���� �ٿ�ε� ��ư ��������
	public void handleFileDownload(ActionEvent e) {
		// 
		
	}
	
	//���� ��ư ��������
	public void handleDelete(ActionEvent e) {
		// 
		
	}
	
	//�̸� ���� ��ư ��������
	public void handleChangeName(ActionEvent e) {
		// 
		
	}
	
	/**
	 * ���� �޼ҵ�
	 */
	
	//���� ��� ����/���� �����ֱ�
	public void ShowList(DirFile dirFile) {
		//
		
	}
	
	//�ߺ����� �˻�
	public boolean IsExist() {
		//
		return false;
	}
	
	//���� ������ �̵�
	public boolean Back() {
		//
		return false;
	}
	
	
}
