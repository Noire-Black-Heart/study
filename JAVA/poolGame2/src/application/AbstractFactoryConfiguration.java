package application;





public interface AbstractFactoryConfiguration {
	
	
	
	
	
	public  Pool_table getPoolTable(String filepath); 
	public  BallCollection getPoolBalls(String filepath);
	public PoolImage getImage(String filepath);
	public PocketCollection getPockets(String filepath);
	
	
}
