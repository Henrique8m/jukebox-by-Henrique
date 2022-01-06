package gui.util;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player// Player class extend BorderPane
{
	// in order to divide the media
	// player into regions
	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mpane;

	
	
	public Player(String file) { // Default constructor
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);
		mpane = new Pane();
		mpane.getChildren().add(view); // Calling the function getChildren
		player.play(); // Making the video play
	}

}
