package assignment_04_Final;

public class mainApp {
	public static void main(String[]args) {
		Recommender_System app = new Recommender_System();		
		
		System.out.println("*******************************************************************");
		System.out.println("\n\tWelcome to The Recommendation System For last.fm \n");
		System.out.println("*******************************************************************\n");

		System.out.println("\tlist all friends of user - userID is 2:\n");
		app.listFriends(2);
		
		//user 67 does not exist
		System.out.println("\tlist all friends of user - userID is 67:\n");
		app.listFriends(67);

		System.out.println("\n\tlist all common friends of users 12 and 46:\n");
		app.commonFriends(12, 46);
		
		//users do not share a common friend
		System.out.println("\tlist all common friends of users 4 and 2:\n");
		app.commonFriends(4, 2);
		
		System.out.println("\n*****************************************************************\n");

		System.out.println("\tlist all common artist of users 4 and 2:\n");
		app.listArtists(4, 2);

		System.out.println("\n\tlist the top 10 most popular artists:\n");
		app.listTop10();

		System.out.println("\n\tlist the top 10 most popular artists amongst user and their friends - userID is 9:\n");
		app.recommend10(9);
	}
}
