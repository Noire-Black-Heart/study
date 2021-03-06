package application;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  

public class Main extends Application {




  public static void main(String[] args) { launch(args); }


  public Pair<Point2D, Point2D> calculateCollision(Point2D positionA, Point2D velocityA, double massA, Point2D positionB, Point2D velocityB, double massB) {


      Point2D collisionVector = positionA.subtract(positionB);
      collisionVector = collisionVector.normalize();


      double vA = collisionVector.dotProduct(velocityA);
      double vB = collisionVector.dotProduct(velocityB);


      if (vB <= 0 && vA >= 0) {
          return new Pair<>(velocityA, velocityB);
      }


      double optimizedP = (2.0 * (vA - vB)) / (massA + massB);


      Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
      Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

      return new Pair<>(velAPrime, velBPrime);
  }
  public void setAfterCollision(BallCollection balls, Bounds table_bounds) {
	  for(PoolBalls traverse_block : balls.getBalls()){
	    	 for (PoolBalls static_bloc : balls.getBalls()) {
		      if (traverse_block != static_bloc) {
		    	  checkBallCollisions( traverse_block,  static_bloc, table_bounds);
		        }
		      }
	    }
  }
  //for pockets
  public void setAfterPockets(BallCollection balls, PocketCollection pocs, Bounds table_bounds) {
	  for(PoolBalls traverse_block : balls.getBalls()){
	    	 for(Pocket poc : pocs.getPockets()) {
		    	  checkPocketCollisions( traverse_block,  poc, table_bounds);
	    	 }
		        }
		      }
	    
  
  
  public void checkBallCollisions(PoolBalls traverse_block, PoolBalls static_bloc, Bounds table_bounds) {

		   double deltaX = traverse_block.getCenterX() - static_bloc.getCenterX();
		   double deltaY = traverse_block.getCenterY() - static_bloc.getCenterY();
		   double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

		   if(distance <= traverse_block.getRadius() + static_bloc.getRadius()){



			   Point2D posA = new Point2D(traverse_block.getCenterX(), traverse_block.getCenterY());
		 	   Point2D velA = new Point2D(traverse_block.getVelocityX(), traverse_block.getVelocityY());
		 	   double massA = traverse_block.getMass();

		 	   Point2D posB = new Point2D(static_bloc.getCenterX(),static_bloc.getCenterY());
		 	   Point2D velB = new Point2D(static_bloc.getVelocityX(), static_bloc.getVelocityY());
		 	   double massB = static_bloc.getMass();;

		 	   Pair<Point2D, Point2D> results = calculateCollision(posA, velA, massA, posB, velB, massB);



		 	   traverse_block.setVelocityX(results.getKey().getX());
		 	   traverse_block.setVelocityY(results.getKey().getY());

		 	   static_bloc.setVelocityX(results.getValue().getX());
		 	   static_bloc.setVelocityY(results.getValue().getY());

		 	   traverse_block.setCenterX(traverse_block.getCenterX() + traverse_block.getVelocityX() );
		 	   traverse_block.setCenterY(traverse_block.getCenterY() + traverse_block.getVelocityY() );
		 	   checkWallCollsions(traverse_block, table_bounds); // make sure that balls dont go outside
		 	   checkWallCollsions(static_bloc, table_bounds);
		 	   static_bloc.setCenterX(static_bloc.getCenterX() + static_bloc.getVelocityX() );
		 	   static_bloc.setCenterY(static_bloc.getCenterY() + static_bloc.getVelocityY() );

		   }
  }
  
  public void checkPocketCollisions(PoolBalls traverse_block, Pocket static_bloc, Bounds table_bounds) {

	   double deltaX = traverse_block.getCenterX() - static_bloc.getCenterX();
	   double deltaY = traverse_block.getCenterY() - static_bloc.getCenterY();
	   double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

	   if(distance <= traverse_block.getRadius() + static_bloc.getRadius()){

		   traverse_block.setRadius(0);

	   }
}
  
  public void checkWallCollsions(PoolBalls traverse_block, Bounds table_bounds) {
	  if(( traverse_block.getCenterY() >= (table_bounds.getMaxY()  - traverse_block.getRadius()))){

			 traverse_block.setVelocityY(-traverse_block.getVelocityY());


     	  	 traverse_block.setCenterY(traverse_block.getCenterY() + traverse_block.getVelocityY() );

  	  }
  	  if( traverse_block.getCenterY() <= (table_bounds.getMinY()  +  traverse_block.getRadius())){


      	   	traverse_block.setVelocityY(-traverse_block.getVelocityY());


         	 traverse_block.setCenterY(traverse_block.getCenterY() + traverse_block.getVelocityY() );
  	     	//break;
  	  }
  	  if(( traverse_block.getCenterX() <= (table_bounds.getMinX()  + traverse_block.getRadius()))){
      	       		traverse_block.setVelocityX(-traverse_block.getVelocityX());

      		traverse_block.setCenterX(traverse_block.getCenterX() + traverse_block.getVelocityX() );

  	  }
  	  if(( traverse_block.getCenterX() >= (table_bounds.getMaxX()  - traverse_block.getRadius()))){
       	   	traverse_block.setVelocityX(-traverse_block.getVelocityX());

      	   	traverse_block.setCenterX(traverse_block.getCenterX() + traverse_block.getVelocityX() );

  	  }
  }
  public void moveBall(PoolBalls traverse_block, Bounds table_bounds) {
	  	 traverse_block.setCenterX(traverse_block.getCenterX() + traverse_block.getVelocityX() );
	  	 checkWallCollsions(traverse_block, table_bounds); // make sure ball does not go beyond walls
	  	 traverse_block.setCenterY(traverse_block.getCenterY() + traverse_block.getVelocityY() );
	  	 checkWallCollsions(traverse_block, table_bounds);
  }
  public void calculateFriction(PoolBalls traverse_block, double table_friction) {
	  if(traverse_block.getVelocityX() > 0) {
			 traverse_block.setVelocityX(traverse_block.getVelocityX() - table_friction);

			 if(traverse_block.getVelocityX() < 0) {
				 traverse_block.setVelocityX(0);
			 }
		 }
		 if(traverse_block.getVelocityX() < 0) {
			 traverse_block.setVelocityX(traverse_block.getVelocityX() + table_friction);
			 if(traverse_block.getVelocityX() > 0) {
				 traverse_block.setVelocityX(0);
			 }
		 }

		 if(traverse_block.getVelocityY() > 0) {
			 traverse_block.setVelocityY(traverse_block.getVelocityY() - table_friction);

			 if(traverse_block.getVelocityY() < 0) {
				 traverse_block.setVelocityY(0);
			 }
		 }
		 if(traverse_block.getVelocityY() < 0) {
			 traverse_block.setVelocityY(traverse_block.getVelocityY() + table_friction);
			 if(traverse_block.getVelocityY() > 0) {
				 traverse_block.setVelocityY(0);
			 }
		 }
  }
  // cue stick physics function
  public void setDragListeners(final PoolBalls block, Pane root) {
	    final Delta dragDelta = new Delta();

	    block.setOnMousePressed(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        // record a delta distance for the drag and drop operation.
	        dragDelta.x = block.getCenterX() - mouseEvent.getSceneX();
	        dragDelta.y = block.getCenterY() - mouseEvent.getSceneY();

	        block.setOpos(block.getCenterX(), block.getCenterY());
	        
	        
	        Cuestick.getInstance().stick.setStartX(block.getCenterX());
	        Cuestick.getInstance().stick.setStartY(block.getCenterY()+50);
	        Cuestick.getInstance().stick.setEndX(block.getCenterX());
	        Cuestick.getInstance().stick.setEndY(block.getCenterY()+150);

	        block.setCursor(Cursor.NONE);
	        
	        
	      }
	    });
	    block.setOnMouseReleased(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {

	    	//calculate the realease direction and set velocity
	    	  double velx = block.getOposX()- block.getChangeX();
	    	  double vely = block.getOposY() - block.getChangeY();
	    	  if(block.getVelocityX() == 0 && block.getVelocityY() == 0) {
	    		 block.setVelocity(velx/7 , vely/7 ); // Sensitivity
	    	  }
	    	  
	    	Cuestick.getInstance().stick.setVisible(false);
	    	
	        block.setCursor(Cursor.HAND);
	      }
	    });
	    block.setOnMouseDragged(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {

	    	  block.setChange(mouseEvent.getSceneX() , mouseEvent.getSceneY()  );
	    	  
	    	  //move the cuestick
	    	  Cuestick.getInstance().stick.setVisible(true);
	    	  moveCueStick(block, Cuestick.getInstance().distance, mouseEvent.getSceneX(), mouseEvent.getSceneY());
	   
		      //the bigger force, the bigger stick
		      double deltaX = Math.abs(block.getCenterX() - mouseEvent.getSceneX());
			  double deltaY = Math.abs(block.getCenterY() - mouseEvent.getSceneY());
			  double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		      
		      Cuestick.getInstance().stick.setStrokeWidth(Math.sqrt(distance/2));
	      }
	    });
	  }
  
  //the method to calculate distance between points simply
  public double calcDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
  
  //the method to move cue stick ---- this method and its algorithm is changed from https://github.com/dl-eric/GUIApp-CoolPool/blob/master/src/edu/andover/coolpool/model/CueStick.java
  public void moveCueStick(PoolBalls block, double distance, double x, double y) {
	    double cueBallX = block.getCenterX();
		double cueBallY = block.getCenterY();
		
		double distanceBalltoMouse = calcDistance(cueBallX, cueBallY, x, y);
		double distanceEndFromCueBall = distance + Cuestick.getInstance().length;
		
		// Use similar triangles to find start and end points.
		double startX = (distance / distanceBalltoMouse) * (x -	cueBallX) + cueBallX;
		double startY = (distance / distanceBalltoMouse) * (y - cueBallY) + cueBallY;
		double endX = (distanceEndFromCueBall / distanceBalltoMouse) * (x -	cueBallX) + cueBallX;
		double endY = (distanceEndFromCueBall / distanceBalltoMouse) * (y -	cueBallY) + cueBallY;
		
		Cuestick.getInstance().stick.setStartX(startX);
		Cuestick.getInstance().stick.setStartY(startY);
		Cuestick.getInstance().stick.setEndX(endX);
		Cuestick.getInstance().stick.setEndY(endY);
		
	}
  
	  class Delta { double x, y;}

  @Override public void start(Stage primaryStage) throws FileNotFoundException {

	  String filepath = "config.json"; 
	  AbstractFactoryConfiguration TableFactory = FactoryProducer.getFactory("table");
	  AbstractFactoryConfiguration BallFactory = FactoryProducer.getFactory("ball");
	  AbstractFactoryConfiguration PocketFactory = FactoryProducer.getFactory("pocket");
	  AbstractFactoryConfiguration ImageFactory = FactoryProducer.getFactory("image");
	  

	  Pool_table new_table = TableFactory.getPoolTable(filepath);
	  BallCollection balls =  BallFactory.getPoolBalls(filepath);
	  PoolImage poolImg = ImageFactory.getImage(filepath);
	  PocketCollection pockets = PocketFactory.getPockets(filepath);
	  
	  Momento momento = new Momento();
	  
	  momento.nodes = balls.nodes;
	  
	  double table_friction = new_table.getFriction();
	  primaryStage.setTitle("Press white circles and release to execute virtual cue stick");
	  final Pane root = new Pane();
	  Scene scene = new Scene(root, poolImg.getSizex(), poolImg.getSizey());

	  //setting the image
	  Image image = new Image(new FileInputStream(poolImg.getPath())); 
	//Setting the image view 
      ImageView imageView = new ImageView(image); 
    //Setting the position of the image 
      imageView.setX(0); 
      imageView.setY(0);
    //setting the fit height and width of the image view 
      imageView.setFitHeight(poolImg.getSizey()); 
      imageView.setFitWidth(poolImg.getSizex()); 
	  
      new_table.setX(poolImg.getOffx());
      new_table.setY(poolImg.getOffy());
      new_table.setVisible(false);
      
      root.getChildren().add(imageView);
	  root.getChildren().add(new_table);
	  root.getChildren().addAll(balls.getBalls());
	  
	  root.getChildren().addAll(pockets.getPockets());
	  
	  //creating the cuestick
	  Cuestick cuestick = Cuestick.getInstance();
	  
	  //System.out.println(cuestick.stick.getStartX() + " " +cuestick.stick.getEndY());
	 
	  root.getChildren().add(cuestick.getInstance().stick);

	  for (PoolBalls block : balls.getBalls()) {
		  setDragListeners(block, root);
      }
    //The Time line watches for the event when the ball hits a wall
	 Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
             new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent t) {

        	 Bounds table_bounds =  new_table.getBoundsInLocal();

        	 for(PoolBalls traverse_block : balls.getBalls()){

        		 moveBall(traverse_block, table_bounds);
        		 setAfterCollision(balls, table_bounds); // trying to make sure that collision is registered even at high speeds
        		 setAfterPockets(balls, pockets, table_bounds);
        		 calculateFriction(traverse_block, table_friction);
        		 checkWallCollsions(traverse_block, table_bounds);
    	     }
        	 
        	 setAfterCollision(balls, table_bounds);
        	 setAfterPockets(balls, pockets, table_bounds);
        
	}
     }));
	 timeline.setCycleCount(Timeline.INDEFINITE);
     timeline.play();
	 primaryStage.setScene(scene);
	 primaryStage.show();

  }
}
