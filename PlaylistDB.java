package assignment_04_Final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlaylistDB {
	private HashMap<Integer,HashSet<LikedArtist>> playlistDB;
	
	public PlaylistDB() throws FileNotFoundException{
		playlistDB = new HashMap<Integer,HashSet<LikedArtist>>();
		
		try {
			List<String> lines = Files.readAllLines(new File("user_artists.dat").toPath());
			for(int index = 1; index < lines.size(); index++) {
				String line = lines.get(index);
				String[] tokens = line.split("\\t");
				
				int userID = Integer.parseInt(tokens[0]);
				int artistID = Integer.parseInt(tokens[1]);
				int replays = Integer.parseInt(tokens[2]);

				//create an artist that user likes listening to
				LikedArtist likedArtist = new LikedArtist(artistID, replays);
				
				//put artist id to artist object
				addToPlaylist(userID, likedArtist);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addToPlaylist(int userid, LikedArtist likedArtist) {
		if(!playlistDB.containsKey(userid) || playlistDB.isEmpty()) { //new user or first entry into playlist
			HashSet<LikedArtist> user_playlist = new HashSet<>();
			user_playlist.add(likedArtist);
			playlistDB.put(userid, user_playlist);
		}else {//not a new user
			HashSet<LikedArtist> temp = playlistDB.get(userid);
			temp.add(likedArtist);
			playlistDB.put(userid, temp);			
		}
	}
	
	public HashMap<Integer,HashSet<LikedArtist>> getPlaylistDB() {
		return playlistDB;
	}
}
