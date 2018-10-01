package facade;
import reader.*;

import java.util.ArrayList;

import ballBuilder.*;

public class BallFacade implements Facade{
	
	public ArrayList<Ball> FacadeParse(String path) {
		
		BallReaderFactory ballFact = new BallReaderFactory();
		
		ArrayList<Ball> balls = ballFact.CreateReader().parse(path);
		
		return balls;
		
	}
}
