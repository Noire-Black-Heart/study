package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import reader.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.*;

import ballBuilder.Ball;
import facade.BallFacade;
import facade.TableFacade;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
			configPath = "config.json";
		}
		
		// parse the file:
		BallFacade readBall = new BallFacade();
		TableFacade readTable = new TableFacade();
		
		ArrayList<Ball> balls = readBall.FacadeParse(configPath);//ballFact.CreateReader().parse(configPath);
		ArrayList<TableHolder> table = readTable.FacadeParse(configPath);
		
		//load the configs into javafx
		for(Ball b : balls) {
			System.out.println(b.getPositionX());
			
		}
		
		
		
		launch(args);
		
	}
}
