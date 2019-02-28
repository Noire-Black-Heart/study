package application;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Cuestick {
		private static Cuestick instance;
		public Line stick;
		public final double distance = 40;
		public final double length = 100;
		
		private Cuestick(double startX, double startY, double endX, double endY){
			this.stick = new Line(startX, startY, endX, endY);
		}
		
		public static Cuestick getInstance(){
			//lazy initialisation
			if(instance == null){
				instance = new Cuestick(0, 10, 0, 20);
				instance.stick.setStrokeWidth(7);
				instance.stick.setStroke(Color.BROWN);
				instance.stick.setVisible(false);
			}
			return instance;
		}
		
		
}
