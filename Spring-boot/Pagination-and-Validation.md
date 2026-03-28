# 📑 Spring Boot: Pagination & Validation

This guide covers two essential features for building robust APIs: **Pagination** (handling large datasets) and **Bean Validation** (ensuring data integrity).

---

## 1. 📖 Pagination Implementation

Pagination allows us to retrieve data in smaller chunks (pages) rather than loading everything at once, which improves performance.

### 🌐 Controller Layer
The controller receives `page` (the index) and `size` (items per page) as request parameters.

```java
@GetMapping("/page")
public ResponseEntity<Page<Todo>> getTodoByPage(
        @RequestParam int page, 
        @RequestParam int size) {
    return new ResponseEntity<>(todoService.getAllTodobyPage(page, size), HttpStatus.OK);
}
```

### ⚙️ Service Layer
Use `Pageable` and `PageRequest` to handle the database query.

```java
public Page<Todo> getAllTodobyPage(int page, int size) {
    // Create a Pageable object with the desired page and size
    Pageable pageable = PageRequest.of(page, size);
    
    // The JpaRepository .findAll() method automatically handles Pageable
    return todoRepository.findAll(pageable);
}
```

---

## 2. ✅ Bean Validation

Validation ensures that the incoming data meets specific requirements before it's saved or processed.

### Common Validation Annotations:
| Annotation | Description | Example |
| :--- | :--- | :--- |
| **`@NotBlank`** | Field must not be null and must contain at least one non-whitespace character. | `String title` |
| **`@NotNull`** | Field must not be null. | `Long id` |
| **`@Email`** | Field must be a well-formed email address. | `String email` |
| **`@Size`** | Field size must be within specified boundaries. | `min=2, max=15` |
| **`@Pattern`** | Field must match a specific regular expression. | `regexp="^[A-Z0-9]+$"` |

### 🏗️ Implementing in the Entity/Model
Apply annotations directly to the fields in your entity class.

```java
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String isCompleted;

    @Email(message = "Please provide a valid email")
    private String email;
}
```

---

## 3. 🛡️ Enabling Validation in Controller

To trigger the validation process, use the **`@Valid`** annotation in your controller's request methods.

```java
@PostMapping("/create")
public ResponseEntity<Todo> createUser(@Valid @RequestBody Todo todo) {
    // If validation fails, Spring Boot returns a 400 Bad Request automatically
    return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
}
```

---

## 📦 Required Dependency

Add this starter to your `pom.xml` to enable validation features:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## 🔄 Workflow Summary Recap

1.  **Pagination**:
    - Define `@RequestParam` for `page` and `size`.
    - Create `PageRequest.of(page, size)`.
    - Pass `pageable` to the repository's `.findAll()`.
2.  **Validation**:
    - Add `spring-boot-starter-validation` dependency.
    - Annotate Model fields (`@NotBlank`, `@Email`, etc.).
    - Use `@Valid` in the Controller method signature.
3.  **Result**: Clean, structured data retrieval and automated error handling for invalid input.
