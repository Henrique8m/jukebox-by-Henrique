package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import songs.Directory;
import songs.Songs;
import songs.playlist.PlayList;
import teclas.TeclaAtalho;
import util.Utils;


public class MainController implements Initializable{
	@FXML
	private Pane pane;
	@FXML	
	private Label songLabel, folderName, creditos;
	@FXML	
	private Button playButton, pauseButton,selectItem;
	@FXML
	private Slider volumeSlider;
	@FXML
	private ProgressBar songProgressBar;
	@FXML
	private TableView<Songs> tableView;
	@FXML
	private TableColumn<Songs, String> tableColumnName;
	@FXML
	private TableView<PlayList> tableViewPlayList;
	@FXML
	private TableColumn<PlayList, String> tableColumnNamePlayList;
	@FXML
	public ImageView imageView01, imageView02, imageView03, imageView04, imageView05;
	public Image image;
	
	private File catalago;
	public File[] pastas;
	
	public int pastaAtual, pastaLefht, credito;
	
	public String linkCapa = "\\capa.jpg";
	
	private ObservableList<Songs> obsList;
	private ObservableList<PlayList> obsListPlay;
	
	private Media media;
	private MediaPlayer mediaPlayer;
	
	private Timeline timeline;
	
	public Directory directory = new Directory();
	private TeclaAtalho teclaAtalho = new TeclaAtalho();
	
	private String caminho = "C:\\Catalogo\\Hungria - Copia";
	
	@FXML
	public void onKeyPressed(KeyEvent evt) {
		
		if((evt.getCode() == KeyCode.ADD)&&(credito > 0)) {
			
			if(tableView.getSelectionModel().getSelectedItem() != null) {
				
				obsListPlay = FXCollections.observableArrayList(Utils.addPlayListFile(tableView.getSelectionModel().getSelectedItem().getSongs(), this));
				tableViewPlayList.setItems(obsListPlay);
				creditos.setText(Integer.toString(credito));
				
			}
			
		}
		
		else if(evt.getCode() == KeyCode.ENTER) {
			
			if((Utils.playList != null)&&(Utils.playList.size()>0)) {
				
				musicInstanciate();
				
			}
			
		}
		
		else if(evt.getCode() == KeyCode.SUBTRACT) {
			
			if((Utils.playList != null)) {
				
				if(Utils.playList.size() !=0) {
					
					obsListPlay = FXCollections.observableArrayList(Utils.deletPlayListFile());
					tableViewPlayList.setItems(obsListPlay);
					credito ++;
					creditos.setText(Integer.toString(credito));
					
				}
				
			}
			
		}
		
		else if(evt.getCode() == KeyCode.F1) {
			
			credito ++;
			creditos.setText(Integer.toString(credito));
			
		}
		
		else if(evt.getCode() == KeyCode.F10) {
						
		}
		
		else if(evt.getCode() == KeyCode.DELETE) {
			
			Utils.playList.clear();
			updateTableViewPlayList();
			mediaPlayer.stop();
			songLabel.setText("JukeBox");
			
		}
		
		else if((evt.getCode() == KeyCode.RIGHT) || (evt.getCode() == KeyCode.LEFT)) {
			
			teclaAtalho.onKeyPress(evt, this);
			directory.addFiles(pastas[pastaAtual].getAbsolutePath());
			folderName.setText(pastas[pastaAtual].getName());
			
		}
				
		updateTableView();
		
	}
	
	
	@FXML
	public void selectItemTableView() {
		
		if(tableView != null)
		System.out.println(tableView.getSelectionModel().getSelectedItem().getName());
	}
	
	private void initializeNodes() {
		
		tableColumnNamePlayList.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));	
		
	}
	
	@FXML
	public void updateTableView() {

		obsList = FXCollections.observableArrayList(directory.listSongs);
		tableView.setItems(obsList);
	
	}
	
	public void updateTableViewPlayList() {

		obsListPlay = FXCollections.observableArrayList(Utils.playList);
		tableViewPlayList.setItems(obsListPlay);
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		directory.addFiles(caminho);
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
				
			}
		});
	
		songProgressBar.setStyle("-fx-accent: #00FF00;");
		//tableColumnName.setStyle("-fx-accent: white; -fx-background-color: #222222; -fx-text-fill: green; -fx-border-color: #222222;");
		//tableColumnName.setStyle("-fx-accent: #00FF00;");
		//tableView.setStyle("-fx-text-fill : white;");
		initializeNodes();
		updateTableView();
		
		catalago = new File("E:\\Catalogo");
		pastas = catalago.listFiles();	
		
		
		pastaLefht = pastas.length;
		pastaAtual = 0;

		image = new Image(pastas[pastaLefht - 2].toURI().toString() + linkCapa);
		imageView01.setImage(image);
		image = new Image(pastas[pastaLefht - 1].toURI().toString() + linkCapa);
		imageView02.setImage(image);
		image = new Image(pastas[pastaAtual].toURI().toString() + linkCapa);
		imageView03.setImage(image);
		image = new Image(pastas[pastaAtual+1].toURI().toString() + linkCapa);
		imageView04.setImage(image);		
		image = new Image(pastas[pastaAtual+2].toURI().toString() + linkCapa);
		imageView05.setImage(image);

	
	}
	
	public void beginTimer() {
		
	    timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
	    	
			double current = mediaPlayer.getCurrentTime().toSeconds();
			double end = media.getDuration().toSeconds();
			songProgressBar.setProgress(current/end);
			
			if(current/end == 1 ) {
				
				cancelTimer();
				next();
				
			}	
	    }));
	    
	    timeline.setCycleCount(Animation.INDEFINITE);
	   timeline.play();
	    
	}

	
	public void cancelTimer() {
		
		timeline.stop();
		
	}	

	
	private void next() {
		
		if(Utils.playList.size()>1) {
			Utils.playList.remove(0);
			updateTableViewPlayList();
			musicInstanciate();
			
		}
		
		else {
			
			cancelTimer();
			Utils.playList.remove(0);
			updateTableViewPlayList();
			songLabel.setText("JukeBox");
			
			
		}
	}
	
	private void musicInstanciate() {
		
		media = new Media(Utils.playList.get(0).getFile().toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		songLabel.setText(Utils.playList.get(0).getName());
		mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
		mediaPlayer.play();
		Utils.playList.remove(0);
		beginTimer();
		timeline.play();
		updateTableViewPlayList();
		
	}
	
}
