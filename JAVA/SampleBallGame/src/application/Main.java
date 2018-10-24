package application;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Colors");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 300, Color.WHITE);
        Line blackLine = LineBuilder.create()
            .startX(170)
            .startY(30)
            .endX(20)
            .endY(140)
            .fill(Color.BLACK)
            .strokeWidth(10.0f)
            .translateY(20)
            .build();

        root.getChildren().add(blackLine); 


  
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
