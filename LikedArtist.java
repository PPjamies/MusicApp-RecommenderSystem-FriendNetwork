package assignment_04_Final;

public class LikedArtist implements Comparable{
	private int artistID;
	private int replays;
	
	public LikedArtist(int _artistID, int _replays) {
		artistID = _artistID;
		replays = _replays;
	}
	
	public int getArtistID() {
		return artistID;
	}
	
	public int getReplays() {
		return replays;
	}
	public void setReplays(int n) {
		this.replays = n;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!(obj instanceof LikedArtist))
			return false;
		if(obj == this)
			return true;
		return this.artistID == (((LikedArtist)obj).getArtistID()) /*&&
				this.replays == ((LikedArtist)obj).getReplays()*/;
	}
	
    @Override
    public int hashCode() {
        return artistID;
    }
    
	@Override
	public int compareTo(Object o) {
		if(this.replays < ((LikedArtist)o).getReplays()) {
			return -1;
		}else if(this.replays > ((LikedArtist)o).getReplays()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Artist: " + artistID + " Replays: " + replays);
		return sb.toString();
	}
}
