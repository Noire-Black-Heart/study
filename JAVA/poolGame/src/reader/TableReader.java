package reader;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TableReader implements Reader {

	@Override
	public void parse(String path) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Table section:
			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

			// reading a value from the table section
			String tableColour = (String) jsonTable.get("colour");

			// reading a coordinate from the nested section within the table
			// note that the table x and y are of type Long (i.e. they are integers)
			Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
			Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("x");

			// getting the friction level.
			// This is a double which should affect the rate at which the balls slow down
			Double tableFriction = (Double) jsonTable.get("friction");

			// TODO: delete me, this is just a demonstration:
			System.out.println("Table colour: " + tableColour + ", x: " + tableX + ", y: " + tableY + ", friction: " + tableFriction);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
