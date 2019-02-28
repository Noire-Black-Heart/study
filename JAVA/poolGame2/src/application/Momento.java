package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Momento {
	
	protected ArrayList<PoolBalls> nodes = new  ArrayList<PoolBalls>();

	 public void addBall(PoolBalls ball) {
		 nodes.add(ball);
	 }
	 public ArrayList<PoolBalls> getBalls(){
		 return nodes;
	 }
	 
	 public void update(BallCollection newBalls) {
		 this.nodes.clear();
		 for(PoolBalls ball : newBalls.nodes) {
			 if(ball.getClass().getSimpleName().equals("Cue_pool_ball")) {
        		 
       		  PoolBalls ball_element = new Cue_pool_ball((int)ball.getCenterX(), (int)ball.getCenterY(), (int)ball.getRadius()); 
       		   
       		  
       		  ball_element.setVelocity(ball.getVelocityX(), ball.getVelocityY());
       		  ball_element.setMass(ball.getMass());
       		  ball_element.setFill(Color.WHITE);
       		  this.nodes.add(ball_element);
       		  
       	  }
       	  else {
       		  PoolBalls ball_element = new Reg_pool_ball((int)ball.getCenterX(), (int)ball.getCenterY(), (int)ball.getRadius()); 
       		 ball_element.color = ball.color;
     		  ball_element.setVelocity(ball.getVelocityX(), ball.getVelocityY());
     		  ball_element.setMass(ball.getMass());
     		  ball_element.setFill(Color.WHITE);
     		  this.nodes.add(ball_element);
     		  
       	  }
		 }
		 
	 }
	
}
