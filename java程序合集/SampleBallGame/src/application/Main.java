package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;

public class Main extends Application {

@Override
public void start(Stage primaryStage)
{
try {
     
      //Creating a Group object
     Pane root = new Pane();  //The root of scene graph is a Group node
     // Creating a Scene by passing the group object, height and width
     Scene scene = new Scene(root,600,400); 
     
 //RECTANGLE
     //define a rectangle using its top-left corner, width and height
     Rectangle rect = new Rectangle(0, 10, 30, 100);
     //Use setFill to change the interior color.
     rect.setFill(Color.BLUE);
     //change the color and thickness of the border
     rect.setStroke(Color.WHITESMOKE); 
     rect.setStrokeWidth(10);
     //This moves the x-coordinate of the top-left.
     rect.setTranslateX(150);
     //add the shape to the scene
     root.getChildren().add(rect);

      //setting color to the scene 
       scene.setFill(Color.BLUE); 
      //Setting the title to Stage. 
       primaryStage.setTitle("JavaFX Basic Graphics Demo");

     //Adding the scene to Stage 
      primaryStage.setScene(scene);
     //Displaying the contents of the stage 
       primaryStage.show();
    }  catch(Exception e)
  {
   e.printStackTrace();
  }
 }

public static void main(String[] args) 
   {
    launch(args);
   }
  }