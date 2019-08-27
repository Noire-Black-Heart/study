package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MovingBallTimer extends Application{

    final int WIDTH = 800;
    final int HEIGHT = 600;
    double ballRadius = 20;
    double ballX = 100;
    double ballY = 200;  
    double xSpeed = 8;

    public static void main(String[] args) {

        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Moving Ball with AnimationTimer Class");

      Group root = new Group();
      Scene scene = new Scene(root, WIDTH, HEIGHT);
	   scene.setFill(Color.SKYBLUE); 

        
         Circle ball = new Circle();
         ball.setCenterX(ballX);
         ball.setCenterY(ballY);
         ball.setRadius(ballRadius);
		 ball.setFill(Color.YELLOW); 
	   	root.getChildren().add(ball); 

        stage.setScene(scene);
        stage.show();

        AnimationTimer animator = new AnimationTimer(){

            @Override
            public void handle(long arg0) {

                // UPDATE
                ballX -= xSpeed;

                if (ballX + ballRadius >= WIDTH)
                {
                    ballX = WIDTH - ballRadius;
                    xSpeed *= -1;
                } 
                else if (ballX - ballRadius < 0) 
                {
                    ballX = 0 + ballRadius;
                    xSpeed *= -1;
                }

                // RENDER
               ball.setCenterX(ballX);
            }      
        };

        animator.start();     
    }
}