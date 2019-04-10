package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ToDoListImpl implements ToDoList {
    @Override
    public Task add(Integer id, LocalDateTime dateTime, String location, String description) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Task findOne(int id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findAll(boolean completed) {
        return null;
    }

    @Override
    public List<Task> findAll(LocalDateTime from, LocalDateTime to, Boolean completed) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Task> findAll(Map<Task.Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed, boolean andSearch) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void clear() {

    }
}
