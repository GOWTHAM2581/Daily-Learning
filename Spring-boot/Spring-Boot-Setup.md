# 🍃 Spring Boot Setup

Today I learned how to set up a Spring Boot project from scratch using the industry-standard tools and configurations. This guide outlines the essential steps to get a Spring Boot application up and running.

---

## 🛠️ Prerequisites

Before starting with Spring Boot, ensure you have the following environment setup:

1.  **JDK (Java Development Kit)**: The core requirement for Java development. (Ensure it's installed and `JAVA_HOME` is set).
2.  **IntelliJ IDEA** (Optional but Recommended): A powerful IDE that offers excellent support for Spring Boot features.
3.  **Maven**: The build tool we will be using (handled automatically by the Spring Initializr wrapper).

---

## 🚀 Step-by-Step Initialization

The easiest way to start a new Spring Boot project is through [start.spring.io](https://start.spring.io) (Spring Initializr).

### 1. Project Configuration
On the Spring Initializr page, select the following options:

| Option | Selection | Description |
| :--- | :--- | :--- |
| **Project** | `Maven` | The build automation tool for managing dependencies. |
| **Language** | `Java` | The primary programming language for the project. |
| **Spring Boot** | `Latest Stable Version` | Choose the latest version without "(SNAPSHOT)" or "(M)" tags. |

### 2. Project Metadata
Fill in the details for your specific project:

*   **Group**: The base package name (e.g., `com.learning`).
*   **Artifact**: The project name (e.g., `spring-boot-demo`).
*   **Name**: Display name for the project.
*   **Description**: A brief summary of what the project does.
*   **Package Name**: Automatically generated based on Group and Artifact.
*   **Packaging**: Select `Jar`.
*   **Java Version**: Select the version installed on your machine (e.g., `17` or `21`).

### 3. Adding Dependencies 📦
On the right side, click on **Add Dependencies** and search for:

*   **Spring Web**: Essential for building web applications, including RESTful services using Spring MVC. Uses Apache Tomcat as the default embedded container.

---

---

## 🏗️ Typical Project Structure

After downloading and extracting, your project will follow this standard Maven structure:

```text
spring-boot-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/learning/demo/
│   │   │       └── DemoApplication.java  <-- Entry Point
│   │   └── resources/
│   │       ├── static/                  <-- CSS, JS, Images
│   │       ├── templates/               <-- HTML Templates (Thymeleaf, etc.)
│   │       └── application.properties    <-- Configuration
│   └── test/                            <-- Unit and Integration Tests
├── pom.xml                               <-- Maven Dependencies
└── .mvn/                                 <-- Maven Wrapper
```

---

## 🟢 The Entry Point: @SpringBootApplication

The generated project includes a main class with a critical annotation:

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

*   **@SpringBootApplication**: This single annotation is a "tri-annotation" that combines:
    1.  `@Configuration`: Tags the class as a source of bean definitions.
    2.  `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings.
    3.  `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the package.

---

## 📥 Generating and Opening the Project

1.  **Generate**: Click the **Generate** button at the bottom. This will download a `.zip` file of your project.
2.  **Extract**: Unzip the downloaded folder to your desired workspace.
3.  **Open in IDE**: 
    *   Open IntelliJ IDEA.
    *   Select **Open** and navigate to the extracted folder.
    *   Select the `pom.xml` file and click **Open as Project**.
    *   Wait for Maven to download all the necessary dependencies.

---

## 💡 Key Concepts Learned

> [!TIP]
> **Why use Spring Initializr?**
> It provides a curated list of dependencies that are guaranteed to work together for a specific Spring Boot version, eliminating "dependency hell."

*   **Group vs Artifact**: Think of the **Group** as the "Organization ID" and the **Artifact** as the "Project ID".
*   **Jar vs War**: Spring Boot favors **JAR** packaging because it comes with an embedded server (Tomcat), making it "just run" without external server configuration.

---

## 🏎️ Workflow Summary Recap

1. Navigate to [start.spring.io](https://start.spring.io).
2. Configure **Maven**, **Java**, and **Stable Spring Boot Version**.
3. Set **Metadata** (Group, Artifact) and choose **Packaging: Jar**.
4. Add **Spring Web** dependency.
5. Click **Generate** and extract the zip.
6. Open `pom.xml` in your IDE and start coding!

---

## 🏁 Next Steps
* Explore the project structure (specifically `src/main/java` and `src/main/resources`).
* Create your first `@RestController` to handle HTTP requests.
