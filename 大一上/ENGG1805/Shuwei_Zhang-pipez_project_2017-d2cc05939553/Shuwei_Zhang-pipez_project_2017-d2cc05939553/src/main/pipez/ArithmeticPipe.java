package pipez;

import java.util.HashMap;
import java.util.Map;

import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.PipezExceptionHandler;
import pipez.core.SimpleBlock;

public class ArithmeticPipe implements Pipe{

	@Override
	public String getName() {
		return "Arithmetic Ops";
	}

	Map<String,Operation> fieldOps = new HashMap<>();
	
	private ArithmeticPipe() {}
	
	public static ArithmeticPipe create(Operation...operations) {
		ArithmeticPipe p = new ArithmeticPipe();
		for(Operation op :operations) {
			p.fieldOps.put(op.field, op);
		}
		return p;
	}
	

	public static Add Add(String field, double x) {
		return new Add(field, x);
	}
	
	public static Minus Minus(String field, double x) {
		return new Minus(field, x);
	}
	
	public static Mult Mult(String field, double x) {
		return new Mult(field, x);
	}
	
	public static Div Div(String field, double x) {
		return new Div(field, x);
	}
	
	@Override
	public Block transform(Block block) {
		SimpleBlock sb = new SimpleBlock();
		
		for(String f :block.fields()) {
			String value = block.value(f);
			Operation op = fieldOps.get(f);
			if(op != null) {
				try {
					double operand = Double.parseDouble(value);
					double res = op.applyTo(operand);
					value = Double.toString(res);
				
				}catch(NumberFormatException nfe) {
					PipezExceptionHandler.handle(
							new Exception("Value of Field " + f + " " 
								+ value + " could not be converted to a double.", nfe));
				}
			}
			
			sb.add(f, value);
		}
		
		return sb;
	}

	static abstract class Operation{
		String field;
		public abstract double applyTo(double operand);
	}
	
	private static class Add extends Operation{

		private double operand; 
		
		public Add(String field, double operand) {
			super.field = field;
			this.operand = operand;
		}
		
		@Override
		public double applyTo(double operand) {
			return this.operand + operand;
		}
	}
	
	private static class Minus extends Operation{

		private double operand; 
		
		public Minus(String field, double operand) {
			super.field = field;
			this.operand = operand;
		}
		
		@Override
		public double applyTo(double operand) {
			return operand - this.operand;
		}
	}
	
	private static class Mult extends Operation{

		private double operand; 
		
		public Mult(String field, double operand) {
			super.field = field;
			this.operand = operand;
		}
		
		@Override
		public double applyTo(double operand) {
			return this.operand * operand;
		}
	}
	
	private static class Div extends Operation{

		private double operand; 
		
		public Div(String field, double operand) {
			super.field = field;
			this.operand = operand;
		}
		
		@Override
		public double applyTo(double operand) {
			return operand / this.operand;
		}
	}
	
}
