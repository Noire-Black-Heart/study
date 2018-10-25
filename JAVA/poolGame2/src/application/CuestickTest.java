package application;

public class CuestickTest {
	public static void main(String[] args){
		Cuestick stick1 = Cuestick.getInstance(10, 100, 100, 10);
		
		Cuestick stick2 = Cuestick.getInstance(20, 200, 200, 20);
		
		System.out.println(stick1.getInstance(startX, startY, endX, endY));
		System.out.println(stick2);
	}
}
