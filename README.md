# Task List API

## Project Directory Structure
```
task-list-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── com.example.task_list_api/
│   │   │   │   │   ├── task_list_api/
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   │   ├── TaskController.java
│   │   │   │   │   │   ├── model/
│   │   │   │   │   │   │   ├── Task.java
│   │   │   │   │   │   ├── service/
│   │   │   │   │   │   │   ├── TaskService.java
│   │   │   │   │   │   ├── config/
│   │   │   │   │   │   │   ├── CorsConfig.java
│   ├── resources/
│   │   ├── static/
│   │   ├── template/
│   │   ├── application.properties
│   │   ├── tasks.json
├── target/
├── pom.xml
├── test/
```

## About

This API serves as a backend for a task management application. It provides endpoints for managing tasks, including creating, reading, updating, and deleting tasks.

The tech stack used includes Spring Boot for the backend, with MVC architecture for handling HTTP requests. The frontend is built separately and consumes the API's endpoints. Data is stored in a JSON file (`tasks.json`) and is read and manipulated by the API.

## Endpoints

- Base URL: https://task-list-api-java.onrender.com/api/tasks

### How to Test Endpoints

#### Using cURL

1. **Get All Tasks:**
```bash
curl https://task-list-api-java.onrender.com/api/tasks
```
2. **Get Task by ID:**
```bash
curl https://task-list-api-java.onrender.com/api/tasks/{id}
```
3. **Add New Task:**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"task":"New Task","completed":false}' https://task-list-api-java.onrender.com/api/tasks
```
4. **Update Task:**
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"task":"Updated Task","completed":true}' https://task-list-api-java.onrender.com/api/tasks/{id}
```
5. **Delete Task:**
```bash
curl -X DELETE https://task-list-api-java.onrender.com/api/tasks/{id}
```
#### Using Postman

1. Open Postman and import the provided collection.
2. Test each endpoint by sending the appropriate HTTP request with required parameters.

## Live Demo
The frontend of this application is deployed on Netlify and can be accessed at [Task List UI](https://task-list-ui.netlify.app/).