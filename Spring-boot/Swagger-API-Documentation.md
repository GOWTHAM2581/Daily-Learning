# 📖 Spring Boot: Swagger API Documentation (OpenAPI)

This guide covers how to implement **Swagger** (using SpringDoc OpenAPI) to automatically generate a visual UI for your API documentation.

---

## 1. 🚀 What is Swagger/OpenAPI?

**Swagger** is a powerful suite of tools that helps you design, build, document, and consume RESTful APIs. In Spring Boot, we use **SpringDoc OpenAPI** to generate a visual dashboard where you can interact with your API.

### Key Benefits:
- **Auto-Documentation**: It scans your controllers and automatically generates documentation.
- **Interactive UI**: You can perform GET, POST, PUT, and DELETE requests directly from the Swagger UI, similar to using **Postman**.
- **Visual Representation**: It provides a clean, user-friendly interface to display all your API endpoints.

---

## 2. 📦 Required Dependency

Add this to your `pom.xml` to enable the Swagger UI in your Spring Boot application:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>3.0.2</version>
    <scope>compile</scope>
</dependency>
```

---

## 3. 🛡️ Documenting API Responses

To make your documentation more meaningful, you can define the possible responses for each endpoint using **`@ApiResponses`**.

### Usage in Controller:
```java
@GetMapping("/{id}")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Todo Retrieved Successfully"),
    @ApiResponse(responseCode = "400", description = "Todo was not found")
})
public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
    // Controller logic
}
```

---

## 4. 🧬 Documenting Data Models

You can use the **`@Schema`** annotation on your entity fields to provide clear examples and descriptions in the Swagger UI.

### Usage in Entity:
```java
public class Todo {
    
    @Id
    @GeneratedValue
    Long id;

    @Schema(name = "title", example = "Complete this task", description = "The title of the todo item")
    String title;

    @Schema(name = "description", example = "Prepare the documentation", description = "Detailed info")
    String description;
}
```

---

## 🌐 Accessing Swagger UI

Once you run your application, you can typically access the documentation at the following URL:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## 🔄 Workflow Summary Recap

1.  **Dependency**: Add `springdoc-openapi-starter-webmvc-ui` to your `pom.xml`.
2.  **Controller**: Use `@ApiResponses` to describe what your endpoints return (200, 404, etc.).
3.  **Model**: Annotate your fields with `@Schema` to provide examples for the API consumers.
4.  **Interact**: Open the `/swagger-ui/index.html` link to test and view your APIs directly.
