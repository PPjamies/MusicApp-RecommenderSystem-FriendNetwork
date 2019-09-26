package assignment_04_Final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class MyGraph {
	private int v;	//number of vertices
	private int e;
	private HashSet<Integer>[] adj;

	//initializes an empty graph with 
	//vertices and 0 edges.
	public MyGraph(int v) {
		this.v = v;
		this.e = 0;
		adj = new HashSet[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new HashSet<Integer>();
		}
	}
	
	 public MyGraph() throws FileNotFoundException{
		 try {
		
			//read all the lines in text file
			List<String> lines = Files.readAllLines(new File("user_friends.dat").toPath());
			
			//# vertices correspond to the last id in the text file
			this.v = new Scanner(lines.get(lines.size()-1)).nextInt()+1;
			
			//# edges correspond to the number of lines (friendships) in the text file
			this.e = lines.size();
			
			//initialize adjacency matrix
			adj = new HashSet[v];
			
			//initialize empty graph
			for (int i = 0; i < v; i++) {
				adj[i] = new HashSet<Integer>();
			}
			
			//build graph
			for(String string: lines) {
				Scanner scan= new Scanner(string);
				int v = scan.nextInt();
				int w = scan.nextInt();
				addEdge(v, w); 
			}			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	 }
	    
	//adds the undirected edge v-w to this graph.
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	//returns the vertices adjacent to vertex {@code v}.
	public Iterable<Integer> adj(int v){
		return adj[v];
	}

	//returns the number of vertices in this graph.
	public int v() {
		return v;
	}
	
	//returns the number of edges in this graph.
	public int e() {
		return e;
	}

	//returns the degree of vertex v.
	public int degree(int s) {
		int degree = 0;
		for(int w: adj(s)) 
			degree++;
		return degree;
	}
	
	//returns a string representation of this graph.
	@Override
	public String toString() {
		StringBuilder graphStr =new StringBuilder("");
		for (int i = 0; i < v; i++) {
			graphStr.append(i + ":");
			for(int w:adj[i])
				graphStr.append(w + ":");
			graphStr.append("\n");
		}
		return graphStr.toString();
	}	
}
