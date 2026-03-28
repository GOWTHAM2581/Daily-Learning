# 🌶️ Spring Boot: Lombok & Logging (SLF4J)

This guide covers how to reduce boilerplate code using **Lombok** and how to implement professional **Logging** using SLF4J.

---

## 1. 🦞 What is Lombok?

**Lombok** is a Java library that automatically plugs into your editor and build tools, spicing up your java. It helps reduce **Boilerplate Code**—repetitive sections of code like getters, setters, and constructors that are used with little to no change across different parts of a program.

### Why do we need this?
- **Encapsulation**: Using Java's core principle, we keep fields `private` and provide `public` methods to access or modify them.
- **Readability**: It keeps your classes clean and focused on business logic rather than boilerplate.

---

## 2. 📝 Lombok Annotations

Lombok uses annotations to generate methods at compile time.

| Annotation | Description | Generated Result |
| :--- | :--- | :--- |
| **`@Getter`** | Generates getter methods for all fields. | `public String getTitle()` |
| **`@Setter`** | Generates setter methods for all non-final fields. | `public void setTitle(String t)` |
| **`@Data`** | A shortcut annotation that includes `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@ToString`, and `@EqualsAndHashCode`. | Complete POJO behavior. |
| **`@NoArgsConstructor`** | Generates a constructor with no arguments. | `public Todo() {}` |
| **`@AllArgsConstructor`** | Generates a constructor with 1 argument for every field. | `public Todo(Long id, ...)` |

### Example implementation:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private String description;
}
```

---

## 3. 🪵 Logging with SLF4J

Logging is used to track the application's flow and debug issues in production environments. **SLF4J** (Simple Logging Facade for Java) is the standard abstraction for logging in Spring Boot.

### Implementation with `@Slf4j`
Instead of manually declaring a `Logger` instance, you can use the `@Slf4j` annotation directly on your class.

```java
@Slf4j
@Service
public class TodoService {

    public void processTodo() {
        log.info("Processing starting...");
        
        try {
            // business logic
        } catch (Exception e) {
            log.error("An error occurred during processing", e);
        }
    }
}
```

---

## 📦 Required Dependency

Add this to your `pom.xml` to enable SLF4J support:

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.1.0-alpha1</version>
    <scope>compile</scope>
</dependency>
```

---

## 📊 Log Levels

Use different levels to categorize the importance of your logs:

- **`log.info("Message")`**: Used for general informational messages (e.g., service started, task completed).
- **`log.warn("Warning")`**: Used for non-critical issues that don't stop the application but need attention.
- **`log.error("Error", exception)`**: Used for critical failures. Always include the exception object to capture the stack trace.

---

## 🔄 Workflow Summary Recap

1.  **Lombok**: Annotate your models with `@Data` or `@Getter/@Setter` to remove boilerplate.
2.  **Constructors**: Use `@NoArgsConstructor` and `@AllArgsConstructor` for easy object instantiation.
3.  **Logging**: Use `@Slf4j` to automatically provide a `log` object in your classes.
4.  **Levels**: Choose the appropriate level (`info`, `warn`, `error`) based on the message priority.
