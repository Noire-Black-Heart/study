package application;
import javafx.scene.shape.*;

public class Cuestick {
		private static Cuestick instance;
		public Line stick;
		
		private Cuestick(double startX, double startY, double endX, double endY){
			this.stick = new Line(startX, startY, endX, endY);
		}
		
		public static Cuestick getInstance(){
			//lazy initialisation
			if(instance == null){
				instance = new Cuestick(1, 1, 2, 2);
				instance.stick.setStrokeWidth(10);
			}
			return instance;
		}
		
}
