package application;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.util.Duration;
import reader.BallReaderFactory;
import reader.TableReaderFactory;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ballBuilder.Ball;
import facade.Facade;

import java.util.*;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			ArrayList<Ball> ballOnTable = new ArrayList<Ball>();
			
			//setting up the table as root scene
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,400);
			scene.setFill(Paint.valueOf(Constants.table.get(0).getColour()));
			
			//setting up the balls by adding their corresponding circle to the root, and add them to a run-time arraylist to track them
			 for(Ball b : Constants.balls) {
  				b.getBall().setCenterX(b.getPositionX());
  				b.getBall().setCenterY(b.getPositionY());
  				b.getBall().setRadius(Constants.radius);
  				b.getBall().setFill(Paint.valueOf(b.getColour())); 
  			   	root.getChildren().add(b.getBall());
  			   	ballOnTable.add(b);
  			   	
  			}
			//setting up the stick
			Line stick = new Line();
			stick.setStartX(Constants.balls.get(0).getPositionX()); 
			stick.setStartY(Constants.balls.get(0).getPositionY()); 
			stick.setEndX(100); 
			stick.setEndY(200);
			root.getChildren().add(stick);
			//show the scene
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
            //The Timeline watches for the event when the ball hits a wall

         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                 new EventHandler<ActionEvent>() {


             @Override
             public void handle(ActionEvent t) {
            	 
            	 int dx = 0;
            	int dy = -0;
            	
            	
            	 
             	//move the ball
            	 for(Ball ball : ballOnTable) {
             	ball.getBall().setLayoutX(ball.getBall().getLayoutX() + dx);
             	ball.getBall().setLayoutY(ball.getBall().getLayoutY() + dy);
             	
             
             	//get the bound of wall
           Bounds bounds = root.getBoundsInLocal();
           		//move the stick
           stick.setStartX(ballOnTable.get(0).getPositionX()); 
			stick.setStartY(ballOnTable.get(0).getPositionY()); 

           	//Bounces off the wall
        if( ball.getBall().getLayoutY() >= (bounds.getMaxY() -  ball.getBall().getRadius())){

               	dy = -dy;

                  }
        if( ball.getBall().getLayoutY() <= (bounds.getMinY() + ball.getBall().getRadius())){

        			dy = -dy;

           }
        if( ball.getBall().getLayoutX() >= (bounds.getMaxX() - ball.getBall().getRadius())){
       	 
       	 		dx = -dx;
        		}
        if( ball.getBall().getLayoutX() <= (bounds.getMinX() + ball.getBall().getRadius())){
       	 
	 				dx = -dx;
 		}
//Bounces off the other ball
        
            	 }
             }
         }));
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "config2.json";
		}
		

		Facade.init(configPath);
		
		
		
		launch(args);
		
	}
}
