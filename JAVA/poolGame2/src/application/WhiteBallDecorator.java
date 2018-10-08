package application;

import javafx.scene.paint.Color;

public class WhiteBallDecorator extends BallDecorator {
	
	
	 public WhiteBallDecorator(PoolBalls decoratedBall) {
		 
		
	      super(decoratedBall);	
	  
	 }
	 @Override
	   public void setMass(double mass) {
		 decoratedBall_l.setMass(mass+2);	       
		 paintWhite(decoratedBall_l); 
	   }
	 //decorating function
	public void paintWhite(PoolBalls decoratedBall) {
		decoratedBall.setFill(Color.WHITE);
	}
	@Override
	protected double getMass() {
		// TODO Auto-generated method stub
		return mass; 
	}
	protected void setVelocity(double Velx, double Vely) {
		this.Velx = Velx; 
		this.Vely= Vely;
		
	}
	protected void setVelocityX(double Velx) {
		this.Velx = Velx;
		
	}
	protected double getVelocityX() {
		return Velx;
		
	}
	protected void setVelocityY(double Vely) {
		this.Vely= Vely;
		
	}
	protected double getVelocityY() {
		return Vely; 
		
	}
	protected void setChange(double changeX, double changeY) {
		this.changeX = changeX; 
		this.changeY = changeY; 
	}
	
	protected double getChangeX() {
		return changeX; 
	}
	protected double getChangeY() {
		return changeY; 
	}
	protected void setOpos(double OposX, double OposY) {
		this.OposX = OposX; 
		this.OposY = OposY; 
	}
	protected double getOposX() {
		return OposX; 
	}
	protected double getOposY() {
		return OposY; 
	}
	 
	 
}
