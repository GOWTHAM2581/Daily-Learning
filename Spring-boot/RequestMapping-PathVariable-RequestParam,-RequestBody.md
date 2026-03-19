# 🌐 Spring Boot: Request Mapping & Input Annotations

This guide covers how to route incoming HTTP requests and extract input data from them using Spring Boot annotations.

---

## 1. @RequestMapping & High-Level Routing 🛤️

### Why do we need this?
Repeating the same base URL for every endpoint in a controller is tedious and error-prone. We use `@RequestMapping` at the class level to define a **root route** for the entire controller.

- **Usage**: Define the base path.
- **Benefit**: Individual methods only need to map their specific endpoint names.

**Example**:
```java
@RestController
@RequestMapping("/todo")
public class TodoController {

    // Now maps to /todo/getTask
    @GetMapping("/getTask")
    public String getTask() {
        return "tasks";
    }

    // Now maps to /todo/completedTask
    @GetMapping("/completedTask")
    public String completedTask() {
        return "completed";
    }
}
```

---

## Types of Input Annotations 📥
To handle incoming requests dynamically, Spring provides 3 main annotations to extract data from the HTTP request:
1. **`@PathVariable`**: Best for identifying specific resources (e.g., getting a single item by ID).
2. **`@RequestParam`**: Best for filtering, sorting, or optional query parameters.
3. **`@RequestBody`**: Best for complex data objects or sensitive information like passwords.

---

## 2. @PathVariable: Extracting URL Path Segments 📍

### Why do we need this?
When the resource ID is part of the actual URL path instead of a query string.

- **Usage**: `/{id}` in the mapping and `@PathVariable` in the method argument.

**Example**:
```java
// URL Example: /todo/15
@GetMapping("/{id}")
public String getTodoById(@PathVariable long id) {
    return "TODO with ID: " + id;
}
```

---

## 3. @RequestParam: Query String Filters 🔍

### Why do we need this?
To extract filtering options passed after the `?` in the URL. It's conventionally used for filtering lists or searching.

- **URLs Examples**:
  - `localhost:8080/api/v1/todo?id=12`
  - `localhost:8080/api/v1/todo?id=12&name=raj`

**Example**:
```java
@GetMapping("")
public String getTodoByIdParam(@RequestParam("todoId") long id) {  
    // "todoId" maps to the query parameter `?todoId=...`
    return "Todo with Id " + id;
}
```
> [!TIP]
> Use `@RequestParam` when the inputs are optional filters, and `@PathVariable` when the ID is essential to locate the exact resource.

---

## 4. @RequestBody: Handling Complex Data 📦

### Why do we need this?
When a client submits larger data bodies (like forms or JSON payloads) via `POST` or `PUT`. Particularly crucial for handling **passwords or sensitive information**, which should *never* go in the URL.

- **Usage**: Used primarily with `@PostMapping` and `@PutMapping`.

**Create Example (POST)**:
```java
@PostMapping("/create")
public String createUser(@RequestBody String body) {
    // Typically, the body would be mapped to a DTO or Model class instead of String
    return body; // returns the created resource or confirmation
}
```

**Update Example (PUT)**:
```java
@PutMapping("/update")
public String updateUser(@RequestBody String body) {
    return "updated";
}
```

> [!NOTE]
> For delete operations, we use `@DeleteMapping`. It can also handle `@PathVariable` to specify which entity to delete (e.g., `@DeleteMapping("/{id}")`).

---

## Workflow Summary Cheat Sheet 🔄

| Annotation | HTTP Method Focus | URL Example | Goal |
| :--- | :--- | :--- | :--- |
| **`@RequestMapping`** | At Class-level | `/todo` | Root routing. Eliminates repetition. |
| **`@PathVariable`** | `@GetMapping` | `/todo/12` | Find specific resource by unique ID. |
| **`@RequestParam`** | `@GetMapping` | `/todo?status=done` | Filter, paginations, and query search. |
| **`@RequestBody`** | `@PostMapping` / `@PutMapping`| `{"password": "pw"}` | Submitting sensitive info (passwords) or JSON data. |
