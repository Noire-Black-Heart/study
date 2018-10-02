package ballBuilder;

public class BallBuilder implements Builder {

	String colour;

	Double positionX;

	Double positionY;
	
	Double velocityX;

	Double velocityY;
	
	Double mass;
	
	@Override
	public void setColour(String colour) {
		
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
