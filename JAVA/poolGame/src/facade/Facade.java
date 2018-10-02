package facade;

import application.Constants;
import reader.BallReaderFactory;
import reader.TableReaderFactory;

public class Facade {
	public static void init(String path) {
		BallReaderFactory ballFact = new BallReaderFactory();
		TableReaderFactory tableFact = new TableReaderFactory();
		
		Constants.balls = ballFact.CreateReader().parse(path);
		Constants.table = tableFact.CreateReader().parse(path);
	}
}
