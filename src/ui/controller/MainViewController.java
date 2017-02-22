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
	private String userId = "userId";
	private String searchName = "serachName";
	private String currentPath;
	private static final String localPath = "C:\\";

	/**
	 * 우측 하단 부분
	 */

	@FXML
	private TableView<DirFile> tableList;

	@FXML
	private TableColumn<DirFile, FileObjectIdentifier> tableColType;
	@FXML
	private TableColumn<DirFile, String> tableColName;
	@FXML
	private TableColumn<DirFile, Date> tableColDate;
	@FXML
	private TableColumn<DirFile, String> tableColSize;

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
	// 완료
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
	 * 파일/폴더 검색 취소
	 * 
	 * @param e
	 */
	// 완료
	public void handleSearchCancel(ActionEvent e) {
		// To-do
		textFieldSearch.setText("");
	}

	/**
	 * 폴더 생성
	 * 
	 * @param e
	 */
	// 완료
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
			// 파일 이름 가져오기
			String fileNameWithPath = get() + "/" + currentPath;
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

		/*
		 * To-do 참고 : dirFile list 맨 처음에 '상위폴더로 가기'를 위한 dirFile 객체를 하나 넣어주어야함
		 * FileObjectIdentifier parent = FileObjectIdentifier.valueOf("Parent");
		 * aa.add(new DirFile(0, "...", "1111", 100, null, parent));
		 */

		ObservableList<DirFile> observableList = FXCollections.observableArrayList(dirFile);

		tableList.setItems(observableList);

		// 다중선택
		// tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// To-do : 수정해야할수도 있음, 특히 tableColSize 부분
		tableColType.setCellValueFactory(new PropertyValueFactory<>("flag"));
		tableColName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
		tableColDate.setCellValueFactory(new PropertyValueFactory<>("modifiedDate"));
		tableColSize.setCellValueFactory(new PropertyValueFactory<>("size"));

		tableColType.setStyle("-fx-alignment: CENTER;");
		tableColName.setStyle("-fx-alignment: CENTER;");
		tableColDate.setStyle("-fx-alignment: CENTER;");
		tableColSize.setStyle("-fx-alignment: CENTER;");

		// 파일유형에 이미지 넣는 코드
		tableColType.setCellFactory(
				new Callback<TableColumn<DirFile, FileObjectIdentifier>, TableCell<DirFile, FileObjectIdentifier>>() {
					@Override
					public TableCell<DirFile, FileObjectIdentifier> call(
							TableColumn<DirFile, FileObjectIdentifier> param) {
						TableCell<DirFile, FileObjectIdentifier> cell = new TableCell<DirFile, FileObjectIdentifier>() {
							ImageView imageview = new ImageView();

							int i = 0;

							@Override
							public void updateItem(FileObjectIdentifier item, boolean empty) {
								if (item != null) {
									HBox box = new HBox();
									box.setSpacing(10);
									VBox vbox = new VBox();
								
									box.setStyle("-fx-alignment: CENTER;");

									imageview.setFitHeight(50);
									imageview.setFitWidth(50);
								
									if (item == folder)
										imageview.setImage(new Image(
												MainViewController.class.getResource("img").toString() + "/1.jpg"));
									else if (item == file)
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

		// row 더블클릭 코드
		tableList.setRowFactory(tv ->
		{
			TableRow<DirFile> row = new TableRow<DirFile>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {

					TablePosition pos = tableList.getSelectionModel().getSelectedCells().get(0);
					int t_row = pos.getRow();
					DirFile item = tableList.getItems().get(t_row);
					TableColumn col = pos.getTableColumn();

					if (col.getId().equals("tableColName")) {
						String data = (String) col.getCellObservableValue(item).getValue();

						if ("...".equals(data)) {
							System.out.println("상위로 이동");
						} else {
							DirFile rowData = row.getItem();
							System.out.println(rowData + "---" + data);
						}

					}

				}
			});

			return row;
		});


	}

	/**
	 * 중복 검사
	 * 
	 * @return
	 */
	// Todo
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
	public void SearchedFileDownload() {

	}
}
