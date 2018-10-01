package ballBuilder;

public class BallBuilder implements Builder {

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
	
	@Override
	public void setColour(String colour) {
		// TODO Auto-generated method stub
		this.colour = colour;
	}

	@Override
	public void setPositionX(Double x) {
        this.positionX = x;
    }

	@Override
	  public void setPositionY(Double y) {
        this.positionY = y;
    }

	@Override
	  public void setVelocityX(Double x) {
        this.velocityX = x;
    }

	@Override
	public void setVelocityY(Double y) {
		this.velocityY = y;

	}

	@Override
	 public void setMass(Double m) {
        this.mass = m;
    }

	public Ball getResult() {
		return new Ball(this.colour, this.positionX, this.positionY, this.velocityX, this.velocityY, this.mass);
	}
}
