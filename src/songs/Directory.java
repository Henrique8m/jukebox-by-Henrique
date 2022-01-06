package songs;

import java.io.File;
import java.util.ArrayList;

public class Directory {
	
	private File directory;
	
	
	private File[] files;
	
	public Songs songs;
	
	public ArrayList<Songs> listSongs;	
	
	private String capa = "capa.jpg";
	private int capaFile;
	
	public Directory() {
	}
	
	public void addFiles(String uri) {
		
		ArrayList<Songs> listSongs = new ArrayList<>();
		
		directory = new File(uri);
		
		files = directory.listFiles();
		
		if(files != null) {
			for(int i=0; i < files.length; i++) {
				
				capaFile = files[i].getName().compareTo(capa);
				
				if(capaFile != 0) {
					
					listSongs.add(new Songs(files[i]));
					
				}
				
				
			}
			this.listSongs = listSongs;
		}
		
	}
	
}
