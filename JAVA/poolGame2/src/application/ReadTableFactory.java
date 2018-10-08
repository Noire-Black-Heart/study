package application;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.paint.Color;



public class ReadTableFactory implements AbstractFactoryConfiguration {

	@Override
	public Pool_table getPoolTable(String filepath) {
		
		JSONParser parser = new JSONParser();
		  
	
		 
	  
	    
	    
	    
	    try {
	    	
	      Object obj = parser.parse(new FileReader(filepath));
			
		  JSONObject jsonObject = (JSONObject) obj;
		    
	      JSONObject table = (JSONObject) jsonObject.get("Table"); 
	      //System.out.println(table);
	      String table_colour = (String) table.get("colour");
	      
	      String friction = (String) String.valueOf(table.get("friction")) ; 
	      //System.out.println("string friction:  "+ friction);
	      double friction_number = Double.parseDouble(friction);
	      //System.out.println("number friction:  "+ friction_number);
	      
	      JSONObject size = (JSONObject) table.get("size");
	      
	      String table_x = (String) String.valueOf(size.get("x")) ;
	      String table_y = (String) String.valueOf(size.get("y")) ;
	     
	      int n_table_x = Integer.parseInt(table_x);
	      int n_table_y = Integer.parseInt(table_y);
	      
//	      System.out.println("table x:  "+ d_table_x);
//	      System.out.println("table y:  "+ d_table_y);
	      Pool_table pool = new Pool_table(0, 0, n_table_x, n_table_y);
	      
	      Color c = Color.web(table_colour);
		   pool.setFill(c);
		 
		   pool.setFriction(friction_number);
		  // pool.setStrokeWidth(pool.getPoolWidth());
		  // pool.setStrokeLineJoin(StrokeLineJoin.ROUND);
		  // pool.setStroke(Color.BROWN);
		   
		   return pool; 
        

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
	public BallCollection getPoolBalls(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
