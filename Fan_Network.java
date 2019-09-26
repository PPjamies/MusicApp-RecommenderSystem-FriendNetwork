package assignment_04_Final;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Fan_Network {
	private HashMap<Integer,Artist> artistDB;					//contains information on the artist such as: id and name
	private HashMap<Integer,HashSet<LikedArtist>> playlistDB;	//contains information on each user's playlist
	private HashMap<Integer,LikedArtist> popularityDB;			//contains information on the artists popularity rankings
	private Friend_Network frndNet = new Friend_Network();
	
	public Fan_Network() throws FileNotFoundException{
		//initialize databases
		artistDB = new ArtistDB().getArtistDB();
		playlistDB = new PlaylistDB().getPlaylistDB();
		popularityDB = new PopularityDB().getPopularityDB();	
	}
	
	
	public void printCommonArtistsBetween(int user1, int user2) {
		Iterable<LikedArtist> common_playlist = getCommonArtistsBetween(user1, user2);
		if(common_playlist.equals(null)){
			System.out.println("users do not share a favorite artist");
		}
		for(LikedArtist artist: common_playlist){
			System.out.println(artistDB.get(artist.getArtistID()).getName());
		}
	}
	
	protected Iterable<LikedArtist> getCommonArtistsBetween(int user1, int user2){
		HashSet<LikedArtist> user1_playlist = playlistDB.get(user1);	 
		HashSet<LikedArtist> user2_playlist = playlistDB.get(user2);	
		HashSet<LikedArtist> common_playlist =  new HashSet<LikedArtist>(user1_playlist);
				
		//common artists among playlists
		common_playlist.retainAll(user2_playlist);
		
		return common_playlist;	
	}
	
/********************************************************************************************************************************/	
	
	
	public void printFreqListened() {
		List<LikedArtist> popularityRanking = getFreqListened();
		
		//grab the last 10 (the top 10) frequently listened artists
		for(int i=popularityRanking.size()-1; i>= popularityRanking.size()-10; i--) {
			int artistID = popularityRanking.get(i).getArtistID();
			System.out.println(artistDB.get(artistID).getName());
		}
	}
	protected List<LikedArtist> getFreqListened() {
		Collection<LikedArtist> artistAndTheirPopularity = popularityDB.values();
		List<LikedArtist> popularityRanking = new ArrayList<LikedArtist>(artistAndTheirPopularity);
		Collections.sort(popularityRanking); //sort artist by their listening count in ascending order	
		
		return popularityRanking;
	}
		
/********************************************************************************************************************************/	
	
	
	public void printFreqListenedByUser(int user) {
		List<LikedArtist> popularityRankingAmongstFriends = getFreqListenedByUser(user);
		
		//grab the last 10 (the top 10) frequently listened to artists amongst user and their friends
		//the loop will stop if there are less than 10 songs in user/friend's playlist
		for(int i=popularityRankingAmongstFriends.size()-1; i>=popularityRankingAmongstFriends.size()-10
				&& popularityRankingAmongstFriends.get(i) != null; i--) {
			int artistID = popularityRankingAmongstFriends.get(i).getArtistID();
			System.out.println(artistDB.get(artistID).getName());
		}
	}

	protected List<LikedArtist> getFreqListenedByUser(int user){
		//get user's friends
		Iterable<Integer> friends = frndNet.getFriendsOf(user);	
		
		//HashMap contains artistID as the keys and LikedArtist as the value
		//Hashmap will store the user's (and user's friends') LikedArtists, as well
		//as the LikedArtist's total playcount amongst the friend group.
		HashMap<Integer,LikedArtist> commonArtists = new HashMap<>();
		
		try {
			
			//initialize hashmap with the user's playlist by default
			for(LikedArtist artist: playlistDB.get(user)) {
				commonArtists.put(artist.getArtistID(),artist);
			}

			//Add friend's playlist to the hashmap if artist doesnt already exist
			//If artist does exist in the hashmap, adjust the artist's total play count
			for(Integer friend: friends) 
			{
				for(LikedArtist artist: playlistDB.get(friend)) 
				{
					int artistID = artist.getArtistID();
					int replays = artist.getReplays();
					
					if(commonArtists.containsKey(artistID)) //adjust the total play count
					{	
						int totalPlayCount = commonArtists.get(artistID).getReplays();
						totalPlayCount+=replays;
						commonArtists.get(artistID).setReplays(totalPlayCount);
					}else {
						commonArtists.put(artistID, artist); //insert new artist into hashmap
					}
				}
			}		
		}catch(Exception e) {}
		
		Collection<LikedArtist> artistAndTheirPopularity = commonArtists.values();
		List<LikedArtist> popularityRanking = new ArrayList<LikedArtist>(artistAndTheirPopularity);		
		Collections.sort(popularityRanking); //sort artist by their listening count in ascending order	

		return popularityRanking;
	}
}
