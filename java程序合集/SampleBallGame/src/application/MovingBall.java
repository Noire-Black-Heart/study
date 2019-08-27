package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingBall extends Application {

	@Override
	public void start(Stage stage) {
		 Pane canvas=new Pane();
		 Scene scene = new Scene(canvas, 800, 600);      
		 
		 Circle ball = new Circle();
		 ball.setCenterX(700);
		 ball.setCenterY(100);
		 ball.setRadius(20);
		 ball.setFill(Color.YELLOW); 
		 canvas.getChildren().add(ball); 
		 
          stage.setTitle("Simple ball");
          stage.setScene(scene);
          stage.show();
          
          
          
          Timeline movingBall = new Timeline(new KeyFrame(Duration.seconds(5),  new KeyValue(ball.layoutYProperty(), 1200)));
			
			 movingBall.setCycleCount(5); //The setCycleCount property defines the number of times the animation will run.
		
	    	 movingBall.play();
			
          
	}

	public static void main(String[] args) {
		launch(args);
	}
}