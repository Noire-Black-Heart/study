package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class SimpleBall extends Application {

public void start(Stage stage) {

Pane canvas=new Pane();
Scene scene = new Scene(canvas, 800, 600); 


     Circle ball = new Circle(10, Color.YELLOW);
     ball.relocate(200, 200);
     canvas.getChildren().add(ball); 


      stage.setTitle("Simple ball");
      stage.setScene(scene);
      stage.show();}

public static void main(String[] args)
 {
launch(args);
}
}