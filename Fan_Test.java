package assignment_04_Final;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Fan_Test {
	private Fan_Network fnet;
	
	@BeforeEach
	void init() {
		try {
			fnet = new Fan_Network();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCommonArtistsBetween() {
		int user1 = 2;
		int user2 = 4;
		List<Integer> expected = new ArrayList<>(Arrays.asList(51,53,64,65,70,72,77));
		List<Integer> actual = new ArrayList<>();
		
		//putting components into actual list for comparing
		for(LikedArtist artist: fnet.getCommonArtistsBetween(user1, user2))
			actual.add(artist.getArtistID());
		
		assertTrue(actual.containsAll(expected));
	}
	
	@Test
	void testFreqListened() {
		List<Integer> expected = new ArrayList<>(Arrays.asList(
				289,72,89,292,498,63,284,697,6,296));	
		List<Integer> actual = new ArrayList<>();
		
		//grabbing components into actual list for comparing
		for(LikedArtist artist: fnet.getFreqListened())
			actual.add(artist.getArtistID());

		assertTrue(actual.containsAll(expected));
	}
	
	@Test
	void testFreqListenedByUser() {
		int user = 9;
		List<Integer> expected = new ArrayList<>(Arrays.asList(
				367,1339,9734,187,7414,89,67,1064,63,1001));
		List<Integer> actual = new ArrayList<>();
		
		//grabbing components into actual list for comparing
		for(LikedArtist artist: fnet.getFreqListenedByUser(user))
			actual.add(artist.getArtistID());
		
		assertTrue(actual.containsAll(expected));
	}	
}
