package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import au.edu.sydney.brawndo.erp.todo.Task.Field;

public class ToDoListImpl implements ToDoList{

	
	
	
	@Override
	public Task add(Integer id, LocalDateTime dateTime, String location, String description)
			throws IllegalArgumentException, IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public Task findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll(boolean completed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll(LocalDateTime from, LocalDateTime to, Boolean completed) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll(Map<Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed,
			boolean andSearch) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	
}