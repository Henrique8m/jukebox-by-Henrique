package songs.playlist;

import java.io.File;

public class PlayList {

	private File songsList;
	
	private String name;
	
	public PlayList(File songsList) {
		this.songsList = songsList;
		this.name = songsList.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public File getFile() {
		return songsList;
	}
	
	public String getNameString() {
		return songsList.getName().replaceAll(".mp3", "");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((songsList == null) ? 0 : songsList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayList other = (PlayList) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (songsList == null) {
			if (other.songsList != null)
				return false;
		} else if (!songsList.equals(other.songsList))
			return false;
		return true;
	}


}
