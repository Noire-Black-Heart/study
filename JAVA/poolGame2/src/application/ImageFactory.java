package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImageFactory implements AbstractFactoryConfiguration {

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
	
	public PoolImage getImage(String filepath) {
		JSONParser parser = new JSONParser();
	    
	    try {
	    	
	      Object obj = parser.parse(new FileReader(filepath));
			
		  JSONObject jsonObject = (JSONObject) obj;
		    
	      JSONObject table = (JSONObject) jsonObject.get("Table"); 
	      
	      
	      
	      JSONObject image = (JSONObject) table.get("image");
	      
	      String path = (String) image.get("path"); 
	      
	      JSONObject offset = (JSONObject) image.get("offset");
	      Long ox = (Long) offset.get("x");
	      Long oy = (Long) offset.get("y");
	     
	      JSONObject size = (JSONObject) image.get("size");
	      Long sx = (Long) size.get("x");
	      Long sy = (Long) size.get("y");
	     
	     
	      PoolImage img = new PoolImage();
	      img.setOffx(ox);
	      img.setOffy(oy);
	      img.setPath(path);
	      img.setSizex(sx);
	      img.setSizey(sy);
	      
	
		   return img; 
        

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
	public PocketCollection getPockets(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

}
