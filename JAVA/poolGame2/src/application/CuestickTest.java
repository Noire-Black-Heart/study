package application;

public class CuestickTest {
	public static void main(String[] args){
		Cuestick stick1 = Cuestick.getInstance();
		
		System.out.println(stick1.stick.getStartX() + " " +stick1.stick.getEndY());
		
		stick1.stick.setStartX(20);
		stick1.stick.setEndY(50);
		
		Cuestick stick2 = Cuestick.getInstance();
		
		System.out.println(stick1.stick.getStartX() + " " +stick1.stick.getEndY());
		System.out.println(stick2.stick.getStartX() + " " +stick2.stick.getEndY());
	}
}
