package application;

import javafx.scene.shape.Circle;

public abstract class PoolBalls extends Circle  {
	
	protected  double Velx; 
	protected double Vely; 
	protected double changeX, changeY; 
	protected double OposX, OposY; 
	protected double mass; 
	PoolBalls(int x, int y, int r){
		super(x, y ,r); 
	}
	PoolBalls(){
		
	}
	protected abstract void setMass(double mass); 
	protected abstract double getMass(); 
	protected abstract void setVelocity(double Velx, double Vely);
	protected abstract void setVelocityX(double Velx);
	protected abstract double getVelocityX();
	protected abstract void setVelocityY(double Vely);
	protected abstract double getVelocityY();
	protected abstract void setChange(double changeX, double changeY);
	
	protected abstract double getChangeX();
	protected abstract double getChangeY();
	protected abstract void setOpos(double OposX, double OposY);
	protected abstract double getOposX();
	protected abstract double getOposY();
}
