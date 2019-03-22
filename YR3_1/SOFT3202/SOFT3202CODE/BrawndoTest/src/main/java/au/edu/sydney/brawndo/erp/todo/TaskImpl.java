package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;

public class TaskImpl implements Task{

	private int id;
	private LocalDateTime time;
	private String location;
	private String description;
	private boolean complete;
//	private Field Field;
//	private enum Field{
//		 LOCATION, DESCRIPTION
//	}
	
	//constructor
	public TaskImpl(int id, LocalDateTime dateTime, String location, String description) {
		this.id = id;
		this.time = dateTime;
		this.location = location;
		this.description = description;
		this.complete = false;
	}
	
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public LocalDateTime getDateTime() {
		// TODO Auto-generated method stub
		return time;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return complete;
	}

	@Override
	public void setDateTime(LocalDateTime dateTime) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(dateTime == null) {
			throw new IllegalArgumentException("null input!");
		}
		this.time = dateTime;
	}

	@Override
	public void setLocation(String location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(location == null) {
			throw new IllegalArgumentException("null input!");
		}
		if(location == "") {
			throw new IllegalArgumentException("empty input!");
		}
		if(location.length() > 256) {
			throw new IllegalArgumentException("too long input!");
		}
		this.location = location;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	@Override
	public void complete() throws IllegalStateException {
		// TODO Auto-generated method stub
		if(complete) {
			throw new IllegalStateException("already completed!");
		}
		this.complete = true;
	}

	@Override
	public String getField(Field field) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(field == null) {
			throw new IllegalArgumentException();
		}
		
		if(field == Task.Field.LOCATION) {
			return location;
		}
		
		else if(field == Task.Field.DESCRIPTION) {
			return description;
		}
		else {
			return null;
		}
	}
	
	
	
}