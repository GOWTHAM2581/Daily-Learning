# 🔐 Spring Boot: Security Essentials

This guide covers the fundamental concepts of **Spring Boot Security**, including the distinction between Authentication and Authorization, dependency management, and basic configuration.

---

## 1. 🛡️ Authentication vs Authorization

Spring Security provides a robust framework for handling both identity verification and access control.

| Concept | Question it Answers | Description |
| :--- | :--- | :--- |
| **Authentication** | *Who are you?* | The process of verifying if a user is valid (e.g., login). |
| **Authorization** | *What can you do?* | Determining if an authenticated user has permission to access a specific resource. |

---

## 2. 📦 Required Dependency

Add this to your `pom.xml` to enable Spring Security in your application:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>4.1.0-M4</version>
    <scope>compile</scope>
</dependency>
```

---

## 3. ⚙️ Properties Configuration

For quick testing, you can define a default user directly in your `application.properties` file:

```properties
# Spring Security Default User Configuration
spring.security.user.name = Admin
spring.security.user.password = 1234
```

---

## 4. 🧬 Core Security Implementation

To customize how Spring Security handles requests, create a configuration class using the **`SecurityFilterChain`** bean.

### `Security.java` Implementation:
```java
package Practice.Practice_build;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable Cross-Site Request Forgery (CSRF) for stateful APIs
                .csrf(AbstractHttpConfigurer::disable)
                
                // Configure request authorization
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().authenticated() // All endpoints require login
                )
                
                // Use standard HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
```

---

## 🔄 Workflow Summary Recap

1.  **Authentication**: Focuses on user identity (Is he a valid user?).
2.  **Authorization**: Focuses on user permissions (What can he access?).
3.  **Dependency**: Include `spring-boot-starter-security`.
4.  **Properties**: Configure your basic username and password for testing.
5.  **Config**: Implement `SecurityFilterChain` to disable CSRF and enforce authentication on all endpoints.
