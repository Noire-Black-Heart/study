package application;

import javafx.application.Application;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;


public class FirstFX  extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 640, 480);
        BackgroundImage myBI= new BackgroundImage(new Image("file:123.png"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        stage.setScene(scene);
        stage.show();
    }


}