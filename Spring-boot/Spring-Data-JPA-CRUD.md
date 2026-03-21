# 📔 Spring Boot: Spring Data JPA & CRUD Operations

This guide covers building a persistent Todo Application using **Spring Data JPA**. We will implement the complete flow from the database model to the REST controller.

---

## 1. What is Spring Data JPA? 🧬

### Why do we need this?
The **Java Persistence API (JPA)** allows us to map Java objects to database tables (ORM - Object Relational Mapping). **Spring Data JPA** provides pre-built code for common operations like Create, Read, Update, and Delete (CRUD), significantly reducing the amount of boilerplate code we need to write.

---

## 2. Dependency Configuration 📦

To enable JPA support, add the following starter dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

---

## 3. Defining the Entity Model 🏗️

An **Entity** represents a table in your database. We use annotations to define its structure.

```java
package Practice.Practice_build.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // Marks this class as a database table
public class Todo {
    @Id               // Marks this field as the Primary Key
    @GeneratedValue    // Automatically generates unique IDs
    private long id;

    private String title;
    private String description;
    private boolean isCompleted;

    // Standard Getters and Setters (omitted for brevity)
}
```

> [!NOTE]
> Once defined, Spring Boot will automatically create this table in your H2 (or other configured) database.

---

## 4. The Repository Layer 📂

The **Repository** is an interface that handles all database interactions. By extending `JpaRepository`, we get access to standard CRUD methods like `save()`, `findById()`, and `delete()`.

```java
package Practice.Practice_build;

import Practice.Practice_build.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<EntityType, IDType>
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Custom query methods can be added here if needed
}
```

---

## 5. The Service Layer ⚙️

The **Service** layer contains the business logic. It uses `@Autowired` to inject the Repository and perform operations.

```java
package Practice.Practice_build;

import Practice.Practice_build.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Create or Update
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Read by ID
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    // Read All
    public List<Todo> getAllTask() {
        return todoRepository.findAll();
    }

    // Update
    public Todo UpdateTodo(Todo todo) {
        // save() acts as update if the ID is already present in DB
        return todoRepository.save(todo);
    }

    // Delete
    public void DeleteTodoById(Long id) {
        todoRepository.delete(getTodoById(id));
    }
}
```

---

## 6. The Controller Layer 🌐

The **Controller** exposes REST endpoints to the client. It handles HTTP requests and calls the Service layer.

```java
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // POST: Create a new Todo
    @PostMapping("/create")
    public ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    // GET: Retrieve Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTask(@PathVariable Long id) {
        try {
            Todo task = todoService.getTodoById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // GET: Retrieve all Tasks
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTask() {
        return new ResponseEntity<>(todoService.getAllTask(), HttpStatus.OK);
    }

    // PUT: Update an existing Todo
    @PutMapping
    public ResponseEntity<Todo> UpdateTodoById(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.UpdateTodo(todo), HttpStatus.OK);
    }

    // DELETE: Remove a task
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        todoService.DeleteTodoById(id);
    }
}
```

---

## 🔄 Workflow Summary Recap

1.  **Add Dependency**: Add `spring-boot-starter-data-jpa` to `pom.xml`.
2.  **Entity**: Create a model class with `@Entity`, `@Id`, and `@GeneratedValue`.
3.  **Repository**: Create an interface extending `JpaRepository`.
4.  **Service**: Implement business logic using the repository.
5.  **Controller**: Expose CRUD operations via REST endpoints (`@PostMapping`, `@GetMapping`, etc.).
