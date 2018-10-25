package application;
import javafx.scene.shape.*;

public class Cuestick {
		private static Cuestick instance;
		public Line stick;
		
		private Cuestick(double startX, double startY, double endX, double endY){
			this.stick = new Line(startX, startY, endX, endY);
		}
		
		public static Cuestick getInstance(double startX, double startY, double endX, double endY){
			//lazy initialisation
			if(instance == null){
				instance = new Cuestick(startX, startY, endX, endY);
			}
			return instance;
		}
		
}
