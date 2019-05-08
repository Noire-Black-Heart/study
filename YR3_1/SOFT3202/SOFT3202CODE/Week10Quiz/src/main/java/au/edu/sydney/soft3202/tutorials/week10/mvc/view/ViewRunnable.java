package au.edu.sydney.soft3202.tutorials.week10.mvc.view;

import au.edu.sydney.soft3202.tutorials.week10.mvc.model.Counter;

public class ViewRunnable implements Runnable {

	private Counter counter;
	private String header = "";
	private String[] options = {"", "", ""};
	private String prompt = "";
	private Integer command = null;
	
	public ViewRunnable(Counter counter) {
		this.counter = counter;
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		command = ViewUtils.displayMenu(header, options, prompt);
		
	}

}
