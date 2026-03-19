# 🧩 Spring Boot: IoC, DI & Core Annotations

This guide covers the foundational backend architecture of a Spring application, including the 3-tier layering system and how Spring manages objects via Inversion of Control (IoC) and Dependency Injection (DI).

---

## 1. The 3-Tier Architecture 🏗️

### Why do we need this?
Keeping all logic in one place makes code unscalable and hard to maintain. Spring applications typically use a scalable 3-layer architecture to securely decouple responsibilities.

| Layer | Component | Role |
| :--- | :--- | :--- |
| **Presentation** | `Controller` | The API entry point. Interacts with the outside world (Users/UI) to handle incoming requests. |
| **Business / Logic** | `Service` | The middle layer. Validates inputs, applies business rules, and processes data. |
| **Persistence** | `Repository` | The data layer. Exclusively talks to the Database (DB) to save or retrieve records. |

**Flow**: `Controller` ➡️ `Service` ➡️ `Repository`

---

## 2. Inversion of Control (IoC) & Dependency Injection 🔄

### Why do we need this?
Normally in Java, we manually instantiate objects (e.g., `TodoRepository repo = new TodoRepository()`) and let the Garbage Collector remove unused ones. The control of object lifecycles is entirely on us. 

- **Inversion of Control (IoC)**: We hand over the control of object creation and management entirely to the Spring Framework. Spring acts as a container that holds and lifecycle-manages all our application's objects.
- **Dependency Injection (DI)**: If `TodoService` needs `TodoRepository` to work, `TodoRepository` is considered a **Dependency**. Instead of creating this object ourselves, Spring automatically "injects" it into our service (often via `@Autowired`).

> [!TIP]
> This approach makes applications highly scalable, easily testable, and incredibly loosely coupled!

---

## 3. Core Spring Annotations 🏷️

To tell Spring to manage a class (turn it into a **Bean**), we use specialized annotations. 

- **`@Component`**: The generic annotation for any Spring-managed component (often used for Repositories initially).
- **`@Service`**: A specialized component annotation used for the business logic layer.
- **`@RestController`**: Used for the presentation layer Controller.
- **`@Bean`**: Indicates that a method produces a bean to be managed by the Spring container.
- **`@Autowired`**: Instructs Spring to automatically inject (instantiate and provide) the required dependency. For this to work, both the parent and the child class must be Beans managed by Spring.
- **`@ComponentScan`**: Used at the root level of the application to tell Spring where to scan and search for these annotated classes.

---

## 4. Code Example: Putting it all together 🖥️

Here is a practical multi-file demonstration showing how a Controller, a Service, and a Repository are tied together using Spring Annotations.

```java
// 📂 package Practice.Practice_build;
// 📝 TodoController.java (Presentation Layer)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired // Spring automatically injects the TodoService here
    private TodoService todoService;
    
    @GetMapping("/todoList")
    public String todoList() {
        todoService.printTodo();
        return "Todo List here";
    }

    @GetMapping("/get")
    public String todo() {
        return "Todo List";
    }

    @GetMapping("/completed")
    public String todoTaskCompleted() {
        return "Completed Task";
    }

    @GetMapping("/pending")
    public String todoTaskPending() {
        return "Pending Task";
    }
}
```

```java
// 📂 package Practice.Practice_build;
// 📝 TodoService.java (Business Layer)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Tells Spring to manage this business logic class
public class TodoService {

    // AutoWiring the dependency; no need for `new TodoRepository()`
    @Autowired 
    private TodoRepository todoRepository;

    /* Manual object creation looks like this (Avoid this in Spring!):
     * public TodoService() {
     *     todoRepository = new TodoRepository();
     * }
     */

    public void printTodo() {
        // Utilizing the injected repository to fetch data
        System.out.println(todoRepository.getTodos());
    }
}
```

```java
// 📂 package Practice.Practice_build;
// 📝 TodoRepository.java (Data Access Layer)

import org.springframework.stereotype.Component;

@Component // Tells Spring to manage this data class
public class TodoRepository {

    public String getTodos() {
        return "List of Todo Tasks"; // Mocking a DB call
    }
}
```

---

## Workflow Summary Cheat Sheet 🗂️

| Concept | Explanation | Related Annotation |
| :--- | :--- | :--- |
| **IoC** | Giving up control of object creation to Spring. | `@ComponentScan` |
| **DI** | Spring passing required objects directly into classes. | `@Autowired` |
| **Bean** | Any object created and managed by the Spring Framework.| `@Bean`, `@Component`, `@Service` |
