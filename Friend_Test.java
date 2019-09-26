package assignment_04_Final;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Friend_Test {
	private Friend_Network frnet;
	
	@BeforeEach
	void init() {
		try {
			frnet = new Friend_Network();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test_getFriendsOf() {	
		//get the friends of user 3
		Iterable<Integer> friends = frnet.getFriendsOf(3);
		Collection<Integer> result = new ArrayList<>();
		for(Integer friend: friends)
			result.add(friend);
		
		//expected friendlist of user 3
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(78,255,460,837,1740,1801,1975));
		
		//test: user does have friends and user friends are as expected
		assertTrue(result.size() != 0);
		assertTrue(result.containsAll(expected));
	}	
	
	@Test
	void test_getMutualFriendsOf() {
		//get the mutual friends of user3 and user78
		Iterable<Integer> mutual_friends = frnet.getMutualFriendsOf(3, 78);
		Collection<Integer> result = new ArrayList<>();
		for(Integer friend: mutual_friends)
			result.add(friend);
		
		//expected mutual friendlist of user3 and user 78
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(837,1801,1740,255));
		
		//test
		assertEquals(expected,result);
	}

}
