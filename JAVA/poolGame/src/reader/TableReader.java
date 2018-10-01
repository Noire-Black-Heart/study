package reader;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;

public class TableReader implements Reader {

	@Override
	public ArrayList<TableHolder> parse(String path) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		ArrayList<TableHolder> list = new ArrayList<TableHolder>();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Table section:
			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

			//setting the table object
			TableHolder e = new TableHolder();
			e.setColour((String) jsonTable.get("colour"));
			e.setFriction((Double) jsonTable.get("friction"));
			e.setTableX((Long) ((JSONObject) jsonTable.get("size")).get("x"));
			e.setTableY((Long) ((JSONObject) jsonTable.get("size")).get("y"));
			list.add(e);
			// TODO: delete me, this is just a demonstration:
			System.out.println("Table colour: " + e.getColour() + ", x: " + e.getTableX() + ", y: " + e.getTableY() + ", friction: " + e.getFriction());

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
	}

//	@Override
//	public void parse(String path, ArrayList<BallHolder> list) {
//		// placeholder
//		
//	}

}
