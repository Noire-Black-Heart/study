package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pocket extends Circle {
	Pocket(double x, double y, double r){
		super(x, y ,r);
		this.setFill(Color.BLACK);
	}
	
	Pocket(){
		
	}
	
}
