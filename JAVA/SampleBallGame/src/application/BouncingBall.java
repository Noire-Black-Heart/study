package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBall extends Application {

	@Override
	public void start(Stage stage) {
		 Pane canvas=new Pane();
		 Scene scene = new Scene(canvas, 800, 600);

		 Circle ball = new Circle();
		 ball.setRadius(20);
		 ball.setFill(Color.YELLOW);
		 ball.relocate(5, 5);
		 canvas.getChildren().add(ball);
		 
		 Circle ball2 = new Circle(20, Color.GREEN);
		 ball2.relocate(500, 500);
		 canvas.getChildren().add(ball2);

          stage.setTitle("Bouncing ball");
          stage.setScene(scene);
          stage.show();

             //The Timeline watches for the event when the ball hits a wall

          Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                  new EventHandler<ActionEvent>() {

          	double dx =3; //Step on x or velocity
          	double dy = 4; //Step on y

              @Override
              public void handle(ActionEvent t) {
              	//move the ball
              	ball.setLayoutX(ball.getLayoutX() + dx);
              	ball.setLayoutY( ball.getLayoutY() + dy);
              	//move the second ball
              	//ball2.setLayoutX(ball2.getLayoutX() - 5);
              //	ball2.setLayoutY(ball2.getLayoutY() - dy);

            Bounds bounds = canvas.getBoundsInLocal();


//Bounces off the wall
         if( ball.getLayoutY() >= (bounds.getMaxY() -  ball.getRadius())){

                	dy = -dy;

                   }
         if( ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())){

         			dy = -dy;

            }
         if( ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius())){
        	 
        	 		dx = -dx;
         		}
         if( ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius())){
        	 
 	 				dx = -dx;
  		}
//Bounces off the other ball
        double colli = ball.getRadius() + ball2.getRadius();
        double absX = Math.abs(ball.getLayoutX() - ball2.getLayoutX());
        double absY = Math.abs(ball.getLayoutY() - ball2.getLayoutY());
        double dist = Math.sqrt(Math.pow(absX, 2) + Math.pow(absY, 2) );
        if(dist <= colli){
        	dx = -dx;
        	dy = -dy;
        }

              }
          }));
          timeline.setCycleCount(Timeline.INDEFINITE);
          timeline.play();


	}

	public static void main(String[] args) {
		launch(args);
	}
}