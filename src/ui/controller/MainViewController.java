package ui.controller;

import java.io.File; 
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import db.domain.DirFile;
import db.domain.FileObjectIdentifier;
import fileprocessor.client.FileClient;
import fileprocessor.client.FileClientLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import network.client.TCPReactor;

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
	private Button btnSearchCancel;
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
	private String userId = "skcc";
	private String searchName = "serachName";
	private String currentPath;
	private static final String localPath = "C:\\";

	/**
	 * ���� �ϴ� �κ�
	 */

	@FXML
	private TableView<DirFile> tableList;

	@FXML
	private TableColumn<DirFile, Integer> tableColType;
	@FXML
	private TableColumn<DirFile, String> tableColName;
	@FXML
	private TableColumn<DirFile, Date> tableColDate;
	@FXML
	private TableColumn<DirFile, String> tableColSize;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		alertDevInfo = new Alert(AlertType.INFORMATION);

		// �ʱ� �α��� �� �޴� ����Ʈ
		try {
			System.out.println("[Client]");
			dirFile = fileClient.ShowList(userId, "skcc");
			System.out.println("[Client]");
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
		TCPReactor tcpReactor = new TCPReactor("127.0.0.1", 9900);
		Socket soc = tcpReactor.getSocket();
		fileClient = new FileClientLogic(soc);

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
	// �Ϸ�
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

	
	// ������ ���� ��ư ��������
		public void handleShowDevInfo(ActionEvent e) {
			//
			alertDevInfo.setContentText("SKCC 4��");
			alertDevInfo.show();

		}
	
	
	/**
	 * ����/���� �˻� ���
	 * 
	 * @param e
	 */
	// �Ϸ�
	public void handleSearchCancel(ActionEvent e) {
		// To-do
		textFieldSearch.setText("");
	}

	/**
	 * ���� ����
	 * 
	 * @param e
	 */
	// �Ϸ�
	public void handleCreateFolder(ActionEvent e) {

		String entered = "none.";
		TextInputDialog dialog = new TextInputDialog("hi");
		dialog.setTitle("hi");
		dialog.setHeaderText("Change Name");

		Optional<String> result = dialog.showAndWait();
		String folderNameWithPath = "";
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

			Node source = (Node) e.getSource();
			Window theStage = source.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(theStage);

			String localFilePath = selectedFile.getPath();

			String entered = "none.";
			TextInputDialog dialog = new TextInputDialog("hi");
			dialog.setTitle("FileUpload");
			dialog.setHeaderText("Set Upload File Name :");

			Optional<String> result = dialog.showAndWait();
			String fileNameWithPath = "";
			if (result.isPresent()) {

				fileNameWithPath = currentPath + "/" + result.get();
			}

			dirFile = fileClient.FileUpload(userId, localFilePath, fileNameWithPath);
		} catch (IOException d) {
			d.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

		/*
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
		*/
	}

	/**
	 * ����/���� ����
	 * 
	 * @param e
	 */

	public void handleDelete(ActionEvent e) {
		//
		/*
		try {
			// ���� �̸� ��������
			String fileNameWithPath = get() + "/" + currentPath;
			dirFile = fileClient.DirectoryRemove(userId, fileNameWithPath);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	*/
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

		/*
		 * To-do ���� : dirFile list �� ó���� '���������� ����'�� ���� dirFile ��ü�� �ϳ� �־��־����
		 * FileObjectIdentifier parent = FileObjectIdentifier.valueOf("Parent");
		 * dirFile.add(0, new DirFile(0, "...", "1111", 100, null, parent));
		 */
		
		/*
		if(currentPath != userId) {
			dirFile.add(0, new DirFile(0, "...", "1111", 100, "", 0,"1111"));
		}
		*/

		FileObjectIdentifier folder = FileObjectIdentifier.valueOf("Folder");
		FileObjectIdentifier file = FileObjectIdentifier.valueOf("File");
		FileObjectIdentifier parent = FileObjectIdentifier.valueOf("Parent");
		
		
		ObservableList<DirFile> observableList = FXCollections.observableArrayList(dirFile);

		tableList.setItems(observableList);

		// ���߼���
		// tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// To-do : �����ؾ��Ҽ��� ����, Ư�� tableColSize �κ�
		tableColType.setCellValueFactory(new PropertyValueFactory<>("flag"));
		tableColName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
		tableColDate.setCellValueFactory(new PropertyValueFactory<>("modifiedDate"));
		tableColSize.setCellValueFactory(new PropertyValueFactory<>("size"));

		tableColType.setStyle("-fx-alignment: CENTER;");
		tableColName.setStyle("-fx-alignment: CENTER;");
		tableColDate.setStyle("-fx-alignment: CENTER;");
		tableColSize.setStyle("-fx-alignment: CENTER;");

		// ���������� �̹��� �ִ� �ڵ�
		tableColType.setCellFactory(
				new Callback<TableColumn<DirFile, Integer>, TableCell<DirFile, Integer>>() {
					@Override
					public TableCell<DirFile, Integer> call(
							TableColumn<DirFile, Integer> param) {
						TableCell<DirFile, Integer> cell = new TableCell<DirFile, Integer>() {
							ImageView imageview = new ImageView();

							

							@Override
							public void updateItem(Integer item, boolean empty) {
								if (item != null) {
									
									HBox box = new HBox();
									box.setSpacing(10);
									VBox vbox = new VBox();
								
									box.setStyle("-fx-alignment: CENTER;");

									imageview.setFitHeight(50);
									imageview.setFitWidth(50);
								
									if (item == 0)
										imageview.setImage(new Image(
												MainViewController.class.getResource("img").toString() + "/1.jpg"));
									else if (item == 1)
										imageview.setImage(new Image(
												MainViewController.class.getResource("img").toString() + "/2.jpg"));
									else
										imageview.setImage(new Image(
												MainViewController.class.getResource("img").toString() + "/3.jpg"));

									box.getChildren().addAll(imageview, vbox);
									
									setGraphic(box);
								}
							}
						};
						return cell;
					}
				});

		// row ����Ŭ�� �ڵ�
		tableList.setRowFactory(tv ->
		{
			TableRow<DirFile> row = new TableRow<DirFile>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {

					TablePosition pos = tableList.getSelectionModel().getSelectedCells().get(0);
					int t_row = pos.getRow();
					DirFile item = tableList.getItems().get(t_row);
					TableColumn col = pos.getTableColumn();
					
					//
					

					if (col.getId().equals("tableColName")) {
						String data = (String) col.getCellObservableValue(item).getValue();
						
						
						if(item.getFlag() == 1) {
							System.out.println("���������� �̵�");
							
							//Forword();
						}
						else if(item.getFlag() == 2) {
							System.out.println("���������� �̵�");
							//Back();
							
						}
						
						/*
						if ("...".equals(data)) {
							System.out.println("������ �̵�");
						} else {
							DirFile rowData = row.getItem();
							System.out.println(rowData + "---" + data);
						}
						*/

					}

				}
			});

			return row;
		});


	}

	/**
	 * �ߺ� �˻�
	 * 
	 * @return
	 */
	// Todo
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
	public void SearchedFileDownload() {

	}
	
	/**
	 * �α��� ������ �����κ� ȯ��ǥ��
	 */
	public void setUserId(String userId) {
		this.userId = userId;
		labelUserName.setText(userId + "�� ȯ���մϴ�");
	}
}
