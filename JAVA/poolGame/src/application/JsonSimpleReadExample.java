package application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonSimpleReadExample {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
        	
        	//reading the file and parsing the base
            Object obj = parser.parse(new FileReader("G:\\ball.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
            
            //getting the table configs
            JSONObject table = (JSONObject) jsonObject.get("Table");
            String colour = (String) table.get("colour");
            System.out.println(colour);
            String friction = (String)table.get("friction");
            System.out.println(friction);
            JSONObject size = (JSONObject) table.get("size");
            String x = (String)size.get("x");
            String y = (String)size.get("y");
            System.out.println(x + y);
            
            //getting the ball configs
            long age = (Long) jsonObject.get("Balls");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}