package songs;

import java.io.File;

public class Songs {

	private File songs;
	
	private String name;
	
	public Songs(File songs) {
		this.songs = songs;
		this.name = songs.getName(). replaceAll(".mp3", "");
	}
	
	public String getName() {
		return name;
	}
	
	public File getSongs() {
		return songs;
	}
	
}
