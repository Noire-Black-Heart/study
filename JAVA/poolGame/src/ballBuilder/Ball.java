package ballBuilder;
import javafx.scene.shape.*;
public class Ball {
	String colour;
	
	
	Double positionX;
	
	Double positionY;
	
	Double velocityX;
	
	Double velocityY;
	
	Double mass;
	
	Circle ball;
	//constructor
	public Ball(String colour, Double pX, Double pY, Double vX, Double vY, Double mass) {
		this.colour = colour;
		this.positionX = pX;
		this.positionY = pY;
		this.velocityX = vX;
		this.velocityY = vY;
		this.mass = mass;
		this.ball = new Circle();
	}
	//getters
    public String getColour() {
        return colour;
    }
    
  
    public Double getPositionX() {
        return positionX;
    }
    

    public Double getPositionY() {
        return positionY;
    }
    

    public Double getVelocityX() {
        return velocityX;
    }
    

    public Double getVelocityY() {
        return velocityY;
    }
    

    public Double getMass() {
        return mass;
    }
    
    public Circle getBall() {
    	return ball;
    }
}
