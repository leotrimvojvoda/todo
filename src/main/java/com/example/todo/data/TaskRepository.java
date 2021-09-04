package com.example.todo.data;

import com.example.todo.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Integer> {

    List<Task> getTasksByUserId(int id);

}
