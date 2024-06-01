package com.example.task_list_api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import com.example.task_list_api.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private static final String DATA_FILE = "src/main/resources/tasks.json";
    private List<Task> tasks;

    public TaskService() {
        tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(DATA_FILE)));
            return new Gson().fromJson(json, new TypeToken<List<Task>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveTasks() {
        try {
            String json = new Gson().toJson(tasks);
            Files.write(Paths.get(DATA_FILE), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public Task addTask(Task newTask) {
        int maxId = tasks.stream().mapToInt(Task::getId).max().orElse(0);
        newTask.setId(maxId + 1);
        if (newTask.isCompleted()) {
            newTask.setCompletedTime(java.time.LocalDateTime.now().toString());
        }
        tasks.add(newTask);
        saveTasks();
        return newTask;
    }

    public boolean updateTask(int id, Task updatedTask) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTask(updatedTask.getTask());
            task.setCompleted(updatedTask.isCompleted());
            if (updatedTask.isCompleted()) {
                task.setCompletedTime(java.time.LocalDateTime.now().toString());
            }
            saveTasks();
            return true;
        }
        return false;
    }

    public Task deleteTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            return task;
        }
        return null;
    }
}

