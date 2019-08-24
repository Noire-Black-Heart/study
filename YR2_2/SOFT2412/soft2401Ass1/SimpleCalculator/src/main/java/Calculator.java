package main.java;

import java.util.Scanner;
import java.math.*;

public class Calculator {
	
	public static void main(String[] args){
		
		Scanner in =new Scanner(System.in);
		double a, b,operations,answer= 0.0;
		
		
		System.out.printf("Please enter your first number:");
		a = in.nextDouble();
		System.out.printf("Please enter your second number:");
		b = in .nextDouble();
		System.out.printf("Please choose your operations(number) :\n1.Addition\n2.Subtraction\n3.Multiplication\n4.Division\n5.Power:\n");
		operations = in .nextDouble();
		double addition = 1;
		double subtraction = 2;
		double multiplication = 3;
		double division = 4;
		double power = 5;
		
		if( operations == addition)
		{
			answer = add(a,b);
			System.out.printf("%.2f + %.2f = %.2f \n",a,b,answer);
		}
		if( operations == subtraction)
		{
			answer = sub(a,b);
			System.out.printf("%.2f - %.2f = %.2f \n",a,b,answer);
		}
		if ( operations == multiplication)
		{
			answer = mul(a,b);
			System.out.printf("%.2f * %.2f = %.2f \n",a,b,answer);
		}
		if ( operations == division)
		{
			answer =div(a,b);
			System.out.printf("%.2f / %.2f = %.2f \n",a,b,answer);
			
		}if ( operations == power)
		{
			answer =pow(a,b);
			System.out.printf("%.2f ^ %.2f = %.2f \n",a,b,answer);
		}
		
		
	}
	public static double add(double d,double e){
		double f = d + e;
		return (double)Math.round(f*100)/100;
	}
	public static double sub(double d,double e){
		double f = d - e;
		return (double)Math.round(f*100)/100;
	}

	public static double  mul(double  d, double  e){

		double f = d * e;
		return (double)Math.round(f*100)/100;

	}
	public static double div(double d, double e){
		double f = d / e;
		return (double)Math.round(f*100)/100;
		
	}
	public static double pow(double d, double e) {		
		double f = Math.pow(d, e);		
		return (double)Math.round(f*100)/100;
	}
	


}

