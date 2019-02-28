package application;

import java.util.ArrayList;

public class CompositeBall extends PoolBalls {
	double strength;
	
	protected ArrayList<PoolBalls> nodes = new  ArrayList<PoolBalls>();
	
	
	protected void setStrength(double strength) {
		this.strength = strength;
	}
	
	protected double getStrength() {
		return this.strength;
	}
	
	
	protected void setMass() {
		// TODO Auto-generated method stub
		this.mass = 0;
		for(PoolBalls ball: nodes) {
			this.mass += ball.getMass();
		}
	}

	@Override
	protected double getMass() {
		// TODO Auto-generated method stub
		return this.mass;
		
	}
	
	CompositeBall(int x, int y, int r){
		super(x, y ,r); 
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
		 
	}
	
	protected double getChangeX() {
		return 0; 
	}
	protected double getChangeY() {
		return 0; 
	}
	protected void setOpos(double OposX, double OposY) {
	
	}
	protected double getOposX() {
		return 0; 
	}
	protected double getOposY() {
		return 0; 
	}

	@Override
	protected void setMass(double mass) {
		// TODO Auto-generated method stub
		this.mass = mass;
		
	}
	
}
