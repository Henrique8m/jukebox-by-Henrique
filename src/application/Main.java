package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			Scene scene = new Scene(root);
			
			
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setMaximized(false);
	        primaryStage.setResizable(false);
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.show();
	        
	        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent arg0) {
					
					Platform.exit();
					System.exit(0);
					
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene gerMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {	
		
		launch(args);
	}
}
