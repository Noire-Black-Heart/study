package application;

import java.util.ArrayList;

public class BallCollection {





	 protected ArrayList<PoolBalls> nodes = new  ArrayList<PoolBalls>();

	 public void addBall(PoolBalls ball) {
		 nodes.add(ball);
	 }
	 public ArrayList<PoolBalls> getBalls(){
		 return nodes;
	 }
}
