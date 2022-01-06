package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gui.MainController;
import songs.playlist.PlayList;

public class Utils {
	
	
	public static ArrayList<PlayList> playList;
	
	public final static ArrayList<PlayList> addPlayListFile(File file, MainController main) {
		
		if(playList == null) {
			
			playList = new ArrayList<>();
			playList.add(new PlayList(file));
			main.credito--;
			
		}
		
		else {
			
			if(!hasName(playList, file)) {
				
				playList.add(new PlayList(file));
				main.credito--;	
			
			}
		
		}		
		
		return playList;
		
	}
	
	public final static ArrayList<PlayList> deletPlayListFile() {

		playList.remove(playList.size()-1);
		return playList;
	
	}
	
	public static boolean hasName(List<PlayList> list, File file) {
		PlayList obj = list.stream().filter(x -> x.getFile() == file).findFirst().orElse(null);	
		return obj != null;
	}
	
}
