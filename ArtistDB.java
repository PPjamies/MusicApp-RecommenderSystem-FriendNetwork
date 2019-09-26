package assignment_04_Final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class ArtistDB {
	private HashMap<Integer,Artist> artistDB;
	
	public ArtistDB() throws FileNotFoundException{
		artistDB = new HashMap<Integer,Artist>();
		
		try {
			List<String> lines = Files.readAllLines(new File("artists.dat").toPath());
			for(int index = 1; index < lines.size(); index++) {
				String line = lines.get(index);
				String[] token = line.split("\\t");
				
				int id = Integer.parseInt(token[0]);
				String name = token[1];  

				//put artist id to artist object
				artistDB.put(id,new Artist(id,name));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer,Artist> getArtistDB(){
		return artistDB;
	}
}

