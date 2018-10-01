package ballBuilder;

public class Ball {
	String colour;
	
	// the ball position, velocity, mass are all doubles
	Double positionX;
	// TODO: Double positionY =
	Double positionY;
	// TODO: Double velocityX =
	Double velocityX;
	// TODO: Double velocityY =
	Double velocityY;
	
	Double mass;
	
	public Ball(String colour, Double pX, Double pY, Double vX, Double vY, Double mass) {
		this.colour = colour;
		this.positionX = pX;
		this.positionY = pY;
		this.velocityX = vX;
		this.velocityY = vY;
		this.mass = mass;
	}
	
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
}
