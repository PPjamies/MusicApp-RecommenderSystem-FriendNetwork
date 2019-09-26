package assignment_04_Final;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Friend_Network {
	private MyGraph graph; //user-friend network
	private HashSet<Integer> mutualFriends; //stores mutual friends of user1 and user2
	
	public Friend_Network() throws FileNotFoundException {
		graph = new MyGraph();
	}
	
	//get friendlist of user
	public Iterable<Integer> getFriendsOf(int user) {
		return graph.adj(user);
	}
	
	//print friendlist of user
	public void printFriendsOf(int user){
		Collection<Integer> result = new ArrayList<>();
		
		//save results into an arraylist to run test cases
		for(Integer friends: graph.adj(user))
			result.add(friends);
		
		if(result.isEmpty())
			System.out.println("user does not exist");
		else //print friends
			for(Integer friends: result)
				System.out.println(friends);
	}
	
	//get mutual friends of two users
	public Iterable<Integer> getMutualFriendsOf(int user1, int user2){
		mutualFriends = new HashSet<Integer>();
		
		//get friend list of both users
		Iterable<Integer> friendsOfUser1 = getFriendsOf(user1);
		Iterable<Integer> friendsOfUser2 = getFriendsOf(user2);
		
		//test to see if friend is in friendlist of both user1 and user2
		for(Integer friend1: friendsOfUser1) {
			for(Integer friend2: friendsOfUser2) {
				if(friend2.equals(friend1)) {
					mutualFriends.add(friend2);
				}
			}
		}
		return mutualFriends;
	}
	
	//print mutual frineds of two users
	public void printMutualFriendsOf(int user1, int user2) {
		getMutualFriendsOf(user1,user2); //load the hashSet with mutual friends
		if(mutualFriends.isEmpty()) {
			System.out.println("users do not have a mutual friend");
		}else {
			for(Integer friend: mutualFriends)
				System.out.println(friend);
		}
	}
}
