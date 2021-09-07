package com.example.todo.data;

import com.example.todo.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TaskRepositoryService {

    TaskRepository taskRepository;

    @Autowired
    public TaskRepositoryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void updateTextById(int id, String text)throws Exception{
        Optional<Task> task = taskRepository.findById(id);

        task.orElseThrow(() -> new Exception("Task Not found"));

        System.out.println("FROM TASK REPO SERVICE: "+task.get().toString());

        task.get().setText(text);

        taskRepository.save(task.get());

    }
}
