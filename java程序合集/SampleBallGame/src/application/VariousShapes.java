package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class VariousShapes extends Application {

	@Override
	public void start(Stage stage) {
		
		
		Pane canvas=new Pane();  //The root of scene graph is a layout node
		//create a scene by bypassing the root node and set the resolution
		Scene scene = new Scene(canvas, 800, 800); 
				
		
		//Drawing a rectangle
		//top left x coor, y coor, width, height
		Rectangle rect = new Rectangle(240,280,50,100);
		//color
		rect.setFill(Color.BLUE);
		//edge color
		rect.setStroke(Color.WHITESMOKE);
		//edge width
		rect.setStrokeWidth(10);
		//move top left x to left
		rect.setTranslateX(150);
		//add shape to layout
		canvas.getChildren().add(rect);
		
		//Drawing a circle
		//radius, color
		Circle circle = new Circle(20, Color.YELLOW); 
		//centre location
		circle.relocate(600,600); 
		//add shape
		canvas.getChildren().add(circle);
		
		//Drawing an eclipse
		Ellipse ellipse = new Ellipse(); 
		ellipse.setCenterX(500); 
		ellipse.setCenterY(500); 
		ellipse.setRadiusX(80); 
		ellipse.setRadiusY(50);
		//make it transparent, use setOpacity() in the [0,1] range. (0=invisible, 1=opaque)
		ellipse.setOpacity(0.9);
		ellipse.setFill(Color.RED);
		canvas.getChildren().add(ellipse);
		
		//Drawing a polygon
		Polygon poly = new Polygon(); 
		//polygon is defined by an array of (x,y) coordinates
		poly.getPoints().addAll(new Double[]
				
		{ 
			       200.0, 50.0, 
				   400.0, 50.0, 
				   450.0, 150.0,          
				   400.0, 250.0, 
				   200.0, 250.0,                   
				   150.0, 150.0, 
				
				
		});
		//add color by setFill(Color.***)
		poly.setFill(Color.ALICEBLUE);
		//add the shape
		canvas.getChildren().add(poly);
		
	//Drawing a line
		
		Line line = new Line(); 
		//line is drawn by start coord and end coord.
		line.setStartX(20); 
		line.setStartY(40); 
		line.setEndX(100); 
		line.setEndY(200);
		canvas.getChildren().add(line);
		
		
		stage.setTitle("Various Shapes"); 
		stage.setScene(scene); 
		stage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}