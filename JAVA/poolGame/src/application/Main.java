package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ballBuilder.Ball;
import facade.BallFacade;
import facade.TableFacade;


public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setFill(Paint.valueOf(Constants.table.get(0).getColour()));
			
			for(Ball b : Constants.balls) {
				Circle ball = new Circle();
				ball.setCenterX(b.getPositionX());
		        ball.setCenterY(b.getPositionY());
		        ball.setRadius(Constants.radius);
				ball.setFill(Paint.valueOf(b.getColour())); 
			   	root.getChildren().add(ball);
				
			}
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
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
		
		// parse the file:
		BallFacade readBall = new BallFacade();
		TableFacade readTable = new TableFacade();
		
		Constants.balls = readBall.FacadeParse(configPath);
		Constants.table = readTable.FacadeParse(configPath);
		
		
		
		
		
		launch(args);
		
	}
}
