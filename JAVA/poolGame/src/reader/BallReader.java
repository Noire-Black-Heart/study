package reader;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ballBuilder.Ball;
import ballBuilder.BallBuilder;

import java.util.*;
public class BallReader implements Reader {

	@Override
	public ArrayList<Ball> parse(String path) {

		JSONParser parser = new JSONParser();
		ArrayList<Ball> list = new ArrayList<Ball>();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the "Balls" section:
			JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

			// reading the "Balls: ball" array:
			JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");

			// reading from the array:
			int i = 0;
			for (Object obj : jsonBallsBall) {
				JSONObject jsonBall = (JSONObject) obj;

				//setting the ball object
				BallBuilder builder = new BallBuilder();
				builder.setColour((String)jsonBall.get("colour"));
				builder.setMass((Double) jsonBall.get("mass"));
				builder.setPositionX((Double) ((JSONObject) jsonBall.get("position")).get("x"));
				builder.setPositionY((Double) ((JSONObject) jsonBall.get("position")).get("y"));
				builder.setVelocityX((Double) ((JSONObject) jsonBall.get("velocity")).get("x"));
				builder.setVelocityY((Double) ((JSONObject) jsonBall.get("velocity")).get("y"));
				list.add(builder.getResult());


				System.out.println("Ball colour: "+ list.get(i).getColour() + ", x: " + list.get(i).getPositionX() + ", y: " + list.get(i).getPositionY() + ", mass: " + list.get(i).getMass());
				i ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
	}



}
