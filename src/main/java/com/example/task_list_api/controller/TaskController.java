package com.example.task_list_api.controller;

import com.example.task_list_api.model.Task;
import com.example.task_list_api.service.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private static final Gson gson = new Gson();

    @GetMapping("/")
    public String welcomeMessage() {
        return gson.toJson("Welcome to the Task List API!");
    }

    @GetMapping("api/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
//        List<Task> tasks = taskService.getAllTasks();
//        System.out.println("Fetched tasks: " + tasks);
//        return tasks;
    }

    @GetMapping("api/tasks/{id}")
    public String getTaskById(@PathVariable int id, HttpServletResponse response) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return gson.toJson(task);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return gson.toJson("Task not found");
        }
    }

    @PostMapping("/api/tasks")
    public String addTask(@RequestBody Task newTask, HttpServletResponse response) {
        return gson.toJson(taskService.addTask(newTask));
    }

    @PutMapping("api/tasks/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task updatedTask, HttpServletResponse response) {
        if (taskService.updateTask(id, updatedTask)) {
            return gson.toJson(updatedTask);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return gson.toJson("Task not found");
        }
    }

    @DeleteMapping("api/tasks/{id}")
    public String deleteTask(@PathVariable int id, HttpServletResponse response) {
        Task deletedTask = taskService.deleteTask(id);
        if (deletedTask != null) {
            return gson.toJson(deletedTask);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return gson.toJson("Task not found");
        }
    }
}
