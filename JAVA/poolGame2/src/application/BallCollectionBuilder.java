package application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.scene.paint.Color;
public class BallCollectionBuilder extends Builder{
	
	
 
	BallCollectionBuilder(JSONArray arrayToRead){
		this.arrayToRead = arrayToRead; 
	}
	
	public BallCollection ReadBalls() {
		BallCollection ballCollection = new BallCollection(); 
		if(arrayToRead != null) {
			
			 for(int i=0; i< arrayToRead.size(); i++){
				
				  JSONObject ball = (JSONObject) arrayToRead.get(i);
	        	  JSONObject position  = (JSONObject) ball.get("position"); 
	        	  String colour = (String) ball.get("colour"); 
	        
	        	  Double mass = (Double)  ball.get("mass");
	        	
	        	  
	        	  
	        	  Double d_pX = (Double) position.get("x");
	        	  int n_pX = (int) Math.round(d_pX);
	        	  
	        	  Double d_pY = (Double) position.get("y");
	        	  int n_pY = (int) Math.round(d_pY);

	        	  JSONObject velocity  = (JSONObject) ball.get("velocity");
	        	  Double d_vX = (Double) velocity.get("x");
	        	  Double d_vY = (Double) velocity.get("y");

	        	  if(colour.equals("white")) {
	        		 
	        		  PoolBalls ball_element = new Cue_pool_ball(n_pX, n_pY, 15); 
	        		   
	        		  
	        		  ball_element.setVelocity(d_vX, d_vY);
	        		  ball_element.setMass(mass);
	        		  Color c = Color.web(colour);
	        		  ball_element.setFill(c);
	        		  ballCollection.addBall(ball_element);
	        		  
	        	  }
	        	  else {
	        		  PoolBalls ball_element = new Reg_pool_ball(n_pX, n_pY, 15); 
	        		   
	        		
	        		  
	        		  ball_element.setVelocity(d_vX, d_vY);
	        		  ball_element.setMass(mass);
	        		  Color c = Color.web(colour);
	        		  ball_element.setFill(c);
	        		  ballCollection.addBall(ball_element);
	        		  
	        	  }
	        	  
	          }
			 return ballCollection;
			
		}
		else {
			System.out.println( "Array is null"); 
		}
		
		
		return null; 
	}

}
