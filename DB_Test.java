package assignment_04_Final;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DB_Test {
	private ArtistDB artistDB;
	private PlaylistDB playlistDB;
	private PopularityDB popularityDB;
	
	@BeforeEach
	void init() {
		try {
			artistDB = new ArtistDB();
			playlistDB = new PlaylistDB();
			popularityDB = new PopularityDB();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testArtistDB() {
		//Expected: first artist in database has artistID: 1 and is named: Malice Mizer
		Artist expected = new Artist(1, "MALICE MIZER");
		Artist actual = artistDB.getArtistDB().get(1);
		
		assertTrue(actual.equals(expected));
	}
	
	@Test
	void testPlaylistDB() {
		//Expected: userID:2 listens to artistsID:51 - artistID:100
		Set<Integer> expected = new HashSet<>();
		for(int i=51; i<=100; i++) {
			expected.add(i);
		}
		
		Set<Integer> actual = new HashSet<>();
		for(LikedArtist artist: playlistDB.getPlaylistDB().get(2))
			actual.add(artist.getArtistID());
		
		assertTrue(actual.containsAll(expected));
	}
	
	@Test
	void testPopularityDB() {
		int artistID = 51;
		int expected = 348919;
		
		LikedArtist artist = popularityDB.getPopularityDB().get(artistID);
		int actual = artist.getReplays();
		
		assertTrue(actual == expected);
	}
}
