package application;
import javafx.scene.shape.Rectangle;


public class Pool_table extends Rectangle {
	
	
	private double friction; 

	//private final double stroke_width = 35; 
	
	Pool_table(int x, int y, int w, int h){
		super(x, y , w, h); 
	}
	protected void setFriction(double friction) {
		this.friction = friction; 
	}
	protected double getFriction() {
		return friction; 
	}
	
	
	//protected double getPoolWidth() {
	//	return stroke_width; 
	//}
}
