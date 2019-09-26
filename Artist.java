package assignment_04_Final;

public class Artist{
	private int id;
	private String name;
	
	public Artist(int _id, String _name) {
		id = _id;
		name = _name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!(obj instanceof Artist))
			return false;
		if(obj == this)
			return true;
		return this.id == (((Artist)obj).getId()) &&
				this.name.equalsIgnoreCase(((Artist)obj).getName());
	}
    
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(id + ", " + name);
		return sb.toString();
	}

}
