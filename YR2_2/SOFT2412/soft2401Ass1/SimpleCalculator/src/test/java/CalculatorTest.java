package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Calculator;

public class CalculatorTest {

	//add
	@Test
	public void addTestPositive() {
		double c = Calculator.add(1, 2);
		assertEquals(3, c,0.0001);
	}
	@Test
	public void addTestNegative() {
		double c = Calculator.add(-3, 2);
		assertEquals(-1, c,0.00001);
	}
	//sub
	
	@Test
	public void subTestNegative() {
		double c = Calculator.sub(-3, 2);
		assertEquals(-5, c,0.00001);
	}
	@Test
	public void subTestPositive() {
		double c = Calculator.sub(3, 2);
		assertEquals(1, c,0.00001);
	}
	@Test
	public void subTestZero() {
		double c = Calculator.sub(3, 3);
		assertEquals(0, c,0.00001);
	}
	//mul
	@Test
	public void mulTestPositive() {
		double c = Calculator.mul(4, 5);
		assertEquals(20, c,0.00001);
	}	
	@Test
	public void mulTestDouble() {
		double c = Calculator.mul(4.33333, 5);
		assertEquals(21.67, c,0.00001);
	}
	@Test
	public void mulTestNegative() {
		double c = Calculator.mul(-0.5, 5);
		assertEquals(-2.5, c,0.00001);
	}
	@Test
	public void divTestNegative() {
		double c = Calculator.div(-5, 5);
		assertEquals(-1, c,0.00001);
	}
	@Test
	public void divTestPositve() {
		double c = Calculator.div(5, 5);
		assertEquals(1, c,0.00001);
	}
	@Test
	public void divTestDouble() {
		double c = Calculator.div(5, -3);
		assertEquals(-1.67, c,0.00001);
	}
	public void powTestPostive() {
		double c = Calculator.pow(3, 3);
		assertEquals(27, c,0.00001);
	}
	public void powTestDouble() {
		double c = Calculator.pow(3.2, 2);
		assertEquals(10.24, c,0.00001);
	}
	public void powTestNegative(){
		
		double c = Calculator.pow(-3, 3);
		assertEquals(-27, c,0.00001);
		
	}
	 
	
	
	
	
	
	
	
	


}

