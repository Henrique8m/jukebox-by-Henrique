package songs.playlist;

import java.io.File;

public class PlayListTocando {

	private File songsList;
	
	private String name;
	
	public PlayListTocando(File songsList) {
		this.songsList = songsList;
		this.name = songsList.getName().replaceAll(".mp3", "");
	}
	
	public String getName() {
		return name;
	}
	
	public File getFile() {
		return songsList;
	}
	
}
