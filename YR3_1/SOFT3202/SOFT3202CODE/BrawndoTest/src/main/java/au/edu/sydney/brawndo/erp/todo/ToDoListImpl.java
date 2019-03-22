package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import au.edu.sydney.brawndo.erp.todo.Task.Field;

public class ToDoListImpl implements ToDoList{

	//private List<Task> taskList = new ArrayList<Task>();
	private Map<Integer, Task> taskMap = new HashMap<Integer, Task>();
	private boolean hasFixedID = false;
	private int generatedIDcount = 0;
	
	@Override
	public Task add(Integer id, LocalDateTime dateTime, String location, String description)
			throws IllegalArgumentException, IllegalStateException {
		
		// check: if null is added behind fixed ID
		if(hasFixedID && id == null) {
			throw new IllegalStateException();
		}
		
		//check: if fixed ID overlap with existing ID
		if(taskMap.containsKey(id)) {
			throw new IllegalArgumentException();
		}
		
		
		//note the list has fixed id given
		hasFixedID = true;
		
		//if id is null, generate a new id, also remove the fixed id given note
		if(id == null) {
			hasFixedID = false;
			id = generatedIDcount;
			while(taskMap.containsKey(generatedIDcount)) {
				generatedIDcount ++;
				id = generatedIDcount;
			}
		}
		
		
		Task oof = new TaskImpl(id, dateTime, location, description);
		
		taskMap.put(id, oof);
		
		return oof;
	}

	@Override
	public boolean remove(int id) {
		// if the id is not present
		if(!taskMap.containsKey(id)) {
			return false;
		}
		
		//if the id is present
		taskMap.remove(id);
		
		
		return true;
	}

	@Override
	public Task findOne(int id) {
		// TODO Auto-generated method stub
		if(taskMap.containsKey(id)) {
			return taskMap.get(id);
		}
		
		return null;
	}

	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		List<Task> taskList = new ArrayList<Task>();
		for(Task task : taskMap.values()) {
			taskList.add(task);
		}
		
		return taskList;
	}

	@Override
	public List<Task> findAll(boolean completed) {
		// TODO Auto-generated method stub
		List<Task> taskList = new ArrayList<Task>();
		for(Task task : taskMap.values()) {
			if(task.isCompleted() == completed) {
			taskList.add(task);
			}
		}
		return taskList;
	}

	@Override
	public List<Task> findAll(LocalDateTime from, LocalDateTime to, Boolean completed) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		List<Task> taskList = new ArrayList<Task>();
		for(Task task : taskMap.values()) {
			if(completed == null) {
				if(to == null) {
					if(from == null) {//all null
						taskList.add(task);
					}else {//completed to null
						
						if(task.getDateTime().isAfter(from)) {
							taskList.add(task);
						}
					}
				}else { // to is not null check
					if(from == null) {//completed from null
						if(task.getDateTime().isBefore(to)) {
							taskList.add(task);
						}
					}else {//only complete null check
						if(task.getDateTime().isAfter(from) && task.getDateTime().isBefore(to)) {
							taskList.add(task);
						}
					}
					
				}
				
				
			}else {//complete not null check
				if(to == null) {
					if(from == null) {//complete not null
						if(task.isCompleted() == completed) {
							taskList.add(task);
						}
					}else {//completed to null
						
						if(task.getDateTime().isAfter(from) && task.isCompleted() == completed) {
							taskList.add(task);
						}
					}
				}else { // to is not null check
					if(from == null) {//completed from null
						if(task.getDateTime().isBefore(to) && task.isCompleted() == completed) {
							taskList.add(task);
						}
					}else {//only complete null check
						if(task.getDateTime().isAfter(from) && task.getDateTime().isBefore(to) && task.isCompleted() == completed) {
							taskList.add(task);
						}
					}
					
				}
			}
		}
		return taskList;
	}

	@Override
	public List<Task> findAll(Map<Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed,
			boolean andSearch) throws IllegalArgumentException {
		List<Task> taskList = new ArrayList<Task>();
		List<Task> taskListOr = new ArrayList<Task>();
		if (andSearch==true)
		{
			taskList = findAll(from, to, completed);
			Iterator<Task> it = taskList.iterator(); 
			if(params == null) {
				//do nothing
			}
			else {//params is not null
				
			try {
			for (Field key : params.keySet())
			{
				if (key.equals(Field.LOCATION))
				{
					String locate = params.get(Field.LOCATION);
					//for (Task task : taskList)
					while(it.hasNext())
					{
						
						if (it.next().getLocation() != locate)
						{
							it.remove();
						}
					}
				}
				else // key is description
				{
					String description = params.get(Field.DESCRIPTION);
					//for (Task task : taskList)
					while(it.hasNext())
					{
						String des = params.get(Field.DESCRIPTION);
						if (it.next().getDescription() != des)
						{
							it.remove();
						}
					}
				}
			}
		}catch(IllegalStateException e) {
		e.printStackTrace();
		}
			}
		}
		else
		{
			for (Task task: taskMap.values())
			{
				
				if(params != null) {
				for (Field key:params.keySet())
				{
					if (task.getDescription() == params.get(key) 
					|| task.getLocation() == params.get(key) 
					|| task.getDateTime().isAfter(from) 
					|| task.getDateTime().isBefore(to) 
					|| task.isCompleted()==completed
					){
					taskListOr.add(task);
					break;
					}
				}
				
			}
			
			else {
				if (
						
						 task.getDateTime().isAfter(from) 
						|| task.getDateTime().isBefore(to) 
						|| task.isCompleted()==completed
						){
						taskListOr.add(task);
				}
			}
			return taskListOr;
		}
		}
		taskList.remove(taskList.size() -1);
		return taskList;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		taskMap.clear();
		hasFixedID = false;
		generatedIDcount = 0;
		
	}

	
	
}