package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class PocketFactory implements AbstractFactoryConfiguration {

	@Override
	public Pool_table getPoolTable(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BallCollection getPoolBalls(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PocketCollection getPockets(String filepath) {
		
		JSONParser parser = new JSONParser();
		
		try {
	    	
		      Object object = parser.parse(new FileReader(filepath));
				
			  JSONObject jsonObject = (JSONObject) object;
			    
		      JSONObject table = (JSONObject) jsonObject.get("Table"); 
		    
		      JSONArray pocket_arr = (JSONArray) table.get("pockets");
		      
		      PocketCollection list = new PocketCollection();
		      
		      int i = 0;
				for (Object obj : pocket_arr) {
					JSONObject pocket = (JSONObject) obj;
					
					Pocket poc = new Pocket((Double) ((JSONObject) pocket.get("position")).get("x"), (Double) ((JSONObject) pocket.get("position")).get("y"), (Double) pocket.get("radius"));
					
					list.addBall(poc);


					System.out.println("x: " + list.nodes.get(i).getCenterX() + ", y: " + list.nodes.get(i).getCenterY());
					i ++;
				}
//		      
			   
			   return list; 
	        

		  } catch (FileNotFoundException e1) {
		      e1.printStackTrace();
		  } catch (IOException e2) {
		      e2.printStackTrace();
		  } catch (ParseException e3) {
		      e3.printStackTrace();
		  }
		    
		    return null; 
	}

	@Override
	public PoolImage getImage(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

}
