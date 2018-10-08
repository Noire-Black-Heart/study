package application;




public class Cue_pool_ball extends PoolBalls {
	 
	protected void setMass(double mass) {
		this.mass = mass; 
	}
	protected double getMass() {
		return mass; 
	}
	Cue_pool_ball(int x, int y, int r){
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
