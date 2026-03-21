# 🗄️ Spring Boot: H2 In-Memory Database

This guide explains how to implement and configure the H2 (Hypersonic 2) in-memory database in a Spring Boot application for fast, lightweight local development.

---

## 1. What is H2 Database? 🧠

### Why do we need this?
H2 is an open-source, lightweight Java database that can be embedded in your application or run in server mode. It is primarily used as an **in-memory database**, meaning data is lost when the application stops. This makes it perfect for:
- Unit testing.
- Rapid prototyping.
- Local development without external database setup.

---

## 2. Dependency Configuration 📦

To use H2, you need to add the database dependency to your `pom.xml` file.

**Maven Dependency**:
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <!-- Use the latest stable version -->
    <version>2.2.224</version>
    <scope>runtime</scope>
</dependency>
```

> [!TIP]
> You can find the latest version on the [Maven Repository](https://mvnrepository.com/artifact/com.h2database/h2).

---

## 3. Application Properties Setup ⚙️

In your `src/main/resources/application.properties` file, you need to define the connection details and enable the H2 console.

```properties
# 🏠 Database Connection Settings
# Defines the JDBC URL (todoDB is the name of the in-memory database)
spring.datasource.url=jdbc:h2:mem:todoDB
# Specifies the driver class for H2
spring.datasource.driverClassName=org.h2.Driver
# Authentication credentials
spring.datasource.username=admin
spring.datasource.password=1234

# 🎮 H2 Console Configuration
# Enables the web-based console interface
spring.h2.console.enabled=true
# Optional: Change the console URL path (default is /h2-console)
# spring.h2.console.path=/h2-console

# 🛠️ JPA/Hibernate Settings
# Tells Hibernate which SQL dialect to use
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# Automatically creates/updates the database schema based on entities
spring.jpa.hibernate.ddl-auto=update
```

---

## 4. Accessing the H2 Console 🖥️

Once the application is running, you can access the graphical interface to view and manage your data.

1.  **Open Browser**: Navigate to `http://localhost:9090/h2-console` (or your configured port).
2.  **Login**:
    - **JDBC URL**: Matches `spring.datasource.url` (e.g., `jdbc:h2:mem:todoDB`).
    - **User Name**: `admin`
    - **Password**: `1234`
3.  **Connect**: Click **Connect** to start executing SQL queries.

---

## 🔄 Workflow Summary Recap

1. Find the **H2 Database** dependency on Maven Repository.
2. Add the `<dependency>` to your `pom.xml`.
3. Configure `application.properties` with the Datasource and Console settings.
4. Run the application and access the **Console** via your browser to verify the connection.

---

## 🏁 Next Steps
- Create Entity classes to map Java objects to H2 tables.
- Use **Spring Data JPA** to interact with the database.
