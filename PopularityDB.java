package assignment_04_Final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class PopularityDB {
	private HashMap<Integer,LikedArtist> popularityDB; //records artist listen count
	
	public PopularityDB() throws FileNotFoundException{
		popularityDB = new HashMap<Integer,LikedArtist>();
		
		try {
			List<String> lines = Files.readAllLines(new File("user_artists.dat").toPath());
			for(int index = 1; index < lines.size(); index++) {
				String line = lines.get(index);
				String[] tokens = line.split("\\t");
				
				int artistID = Integer.parseInt(tokens[1]);
				int replays = Integer.parseInt(tokens[2]);

				//add listen count to artist
				addListenCount(artistID,replays);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addListenCount(int artistID, int replays) {			
		if(popularityDB.isEmpty() || !popularityDB.containsKey(artistID)) {
			popularityDB.put(artistID, new LikedArtist(artistID,replays));
		}else {
			LikedArtist likedArtist = popularityDB.get(artistID);
			replays+=likedArtist.getReplays();
			likedArtist.setReplays(replays);
			popularityDB.put(artistID,likedArtist);
		}
	}
	
	public HashMap<Integer,LikedArtist> getPopularityDB(){
		return popularityDB;
	}
}
