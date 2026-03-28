# 🐘 Spring Boot: PostgreSQL Implementation

This guide covers the implementation of a Spring Boot application using **PostgreSQL** as the database. It highlights the standard 3-layer architecture and the necessary configurations to connect to a PostgreSQL instance.

---

## 1. 🏗️ The 3-Layer Architecture

In a standard Spring Boot application, logic is divided into three distinct layers to ensure separation of concerns and maintainability.

| Layer | Responsibility | Key Annotation |
| :--- | :--- | :--- |
| **Controller** | Handles HTTP requests and returns responses (REST API). | `@RestController` |
| **Service** | Contains business logic and orchestrates data flow. | `@Service` |
| **Repository** | Manages database interactions (CRUD operations). | `@Repository` |

### Why this structure?
- **Separation of Concerns**: Each layer has a specific job.
- **Maintainability**: Changes in business logic don't affect how the API is exposed.
- **Flexibility**: Swapping databases only requires minimal changes (mostly in the Repository/Configuration).

---

## 2. 🔄 Database Flexibility

One of the greatest advantages of this 3-layer architecture is the ease of switching databases.

> [!TIP]
> If you decide to change from **H2** to **PostgreSQL** or **MySQL**, you primarily need to:
> 1.  Update the **Database Configuration** in `application.properties`.
> 2.  Ensure the correct **JDBC Driver** is in your `pom.xml`.
> 3.  The **Controller** and **Service** layers usually remain **unchanged**, as they interact with abstractions (interfaces) rather than direct database code.

---

## 3. ⚙️ PostgreSQL Configuration

To connect Spring Boot to PostgreSQL, update your `src/main/resources/application.properties` file:

```properties
# Application Settings
spring.application.name=Practice-build
server.port=9090

# 🐘 PostgreSQL DB Connections
# Format: jdbc:postgresql://[host]:[port]/[database_name]
spring.datasource.url=jdbc:postgresql://localhost:5432/todo
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=1234

# 🌿 JPA / Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
```

### Explaining the Fields:
- `spring.datasource.url`: The connection string for your database (JDBC).
- `spring.datasource.username/password`: Your PostgreSQL credentials.
- `spring.jpa.hibernate.ddl-auto=update`: Automatically creates or updates the database schema based on your `@Entity` classes.
- `spring.jpa.show-sql=true`: Prints the generated SQL queries to the console for debugging.

---

## 📦 Required Dependency

Ensure you have the PostgreSQL driver in your `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 🔄 Workflow Summary Recap

1.  **Dependency**: Add the `postgresql` driver to your Maven `pom.xml`.
2.  **Configuration**: Define connection details in `application.properties`.
3.  **Layers**:
    - **Repository**: Interface extending `JpaRepository`.
    - **Service**: Business logic using the Repository.
    - **Controller**: REST endpoints calling the Service.
4.  **Run**: Spring Boot will use the provided hibernate dialect to communicate with PostgreSQL.
