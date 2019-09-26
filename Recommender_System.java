package assignment_04_Final;

import java.io.FileNotFoundException;

public class Recommender_System{
	private Friend_Network user_friend;
	private Fan_Network user_artist;
	
	public Recommender_System() {
		try {
			user_friend = new Friend_Network();
			user_artist = new Fan_Network();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void listFriends(int user) {
		user_friend.printFriendsOf(user);
	}

	public void commonFriends(int user1, int user2) {
		user_friend.printMutualFriendsOf(user1, user2);
	}

	public void listArtists(int user1, int user2) {
		user_artist.printCommonArtistsBetween(user1,user2);
	}

	public void listTop10() {
		user_artist.printFreqListened();
	}

	public void recommend10(int user) {
		user_artist.printFreqListenedByUser(user);
	}
}
