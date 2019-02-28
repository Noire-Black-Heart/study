package application;

import java.util.ArrayList;

public class PocketCollection {

	protected ArrayList<Pocket> nodes = new  ArrayList<Pocket>();

	 public void addBall(Pocket poc) {
		 nodes.add(poc);
	 }
	 public ArrayList<Pocket> getPockets(){
		 return nodes;
	 }
	
}
