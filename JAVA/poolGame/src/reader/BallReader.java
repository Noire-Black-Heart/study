package reader;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class BallReader implements Reader {

	@Override
	public void parse(String path) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the "Balls" section:
			JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

			// reading the "Balls: ball" array:
			JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");

			// reading from the array:
			for (Object obj : jsonBallsBall) {
				JSONObject jsonBall = (JSONObject) obj;

				// the ball colour is a String
				// TODO: String colour =
				String colour = (String)jsonBall.get("colour");
				
				// the ball position, velocity, mass are all doubles
				Double positionX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
				// TODO: Double positionY =
				Double positionY = (Double) ((JSONObject) jsonBall.get("position")).get("y");
				// TODO: Double velocityX =
				Double velocityX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
				// TODO: Double velocityY =
				Double velocityY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");
				
				Double mass = (Double) jsonBall.get("mass");

				// TODO: delete me, this is just a demonstration:
				System.out.println("Ball colour: "+ colour + ", x: " + positionX + ", y: " + positionY + ", mass: " + mass);
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
