# 🛠️ Maven: The Build Tool

This guide explains what Maven is, why we use it over basic Java compilation commands, how it manages dependencies, and essential commands for the Spring Boot application lifecycle.

---

## ❓ Why do we need a Build Tool?

If you are working with a single Java file, you can easily compile and run it using the standard `javac` command. However, modern enterprise applications are rarely just one file. 

A project typically includes multiple Java source files, configuration properties (like `application.properties`), static files (HTML, CSS, JS), and external libraries. A **build tool** combines all these disparate pieces, compiles the code, and packages them into a single, easily distributed and executable artifact (like a `.jar` or `.war` file).

While there are alternatives like **Gradle**, **Maven** (developed by Apache) is the most widely used build tool in the Java and Spring Boot ecosystem.

---

## 📦 Dependency Management & Transitive Dependencies

One of Maven's most powerful features is how it handles dependencies.

- **Centralized Repository**: Maven downloads required libraries and dependencies from a central online repository.
- **Transitive Dependencies**: If your project needs Library A to run, and Library A secretly needs Library B, and Library B needs Library C... Maven automatically identifies this dependency tree and installs *all* required transitive dependencies for you.
  - *Example*: You add `spring-boot-starter-web`. Maven understands this requires `spring-boot-starter`, which in turn requires `spring-boot`, which might require an embedded server like Tomcat. Maven grabs them all.

---

## 🌐 Embedded Tomcat Server

When using Maven to build a Spring Web project, it often pulls in **Apache Tomcat**. Tomcat is a local web server (a servlet container) that provides an environment for Java code to run. Because Spring Boot embeds Tomcat directly, you don't have to manually install and configure a standalone server on your machine; Maven runs it for you.

---

## 💻 Essential Maven Commands

When a project is generated via Spring Initializr, it usually includes a **Maven Wrapper** (`mvnw` for Linux/Mac or `mvnw.cmd` for Windows). The wrapper ensures that anyone running the project uses the exact same version of Maven, even if they haven't explicitly installed Maven on their system.

> [!WARNING]
> Standard `mvn` commands (like `mvn --version`) will only work if Maven is installed globally on your machine.
> Instead, always use the wrapper (`./mvnw`) included in your project directory.

| Command | Action | Description |
| :--- | :--- | :--- |
| `./mvnw --version` | **Version** | Displays the current Maven version being used by the wrapper. |
| `./mvnw clean` | **Clean** | Deletes the `target/` folder, removing all previously compiled `.class` files and build artifacts. |
| `./mvnw compile` | **Compile** | Compiles your Java source files and recreates the `target/` folder with fresh `.class` files. |
| `./mvnw clean compile` | **Clean & Compile** | A chained command that both cleans the environment and then cleanly compiles the source code. |
| `./mvnw clean test` | **Test** | Compiles and executes all unit tests in the project. |
| `./mvnw clean verify` | **Verify** | Runs checks on results of integration tests to ensure quality criteria are met. |
| `./mvnw clean package` | **Package** | Compiles the code, runs tests, and packages the compiled code into a distributable format (like a `.jar` or `.war` file). |
| `./mvnw clean install` | **Install** | Packages the application and installs the artifact into your local Maven repository (usually `~/.m2`), allowing other local projects to use it as a dependency. |

---

## 🔄 Workflow Summary Recap

During daily development, your standard workflow will look like this:
1. Write Java code.
2. Run `./mvnw clean compile` to ensure everything builds correctly.
3. Run `./mvnw clean test` to verify your changes haven't broken any existing logic.
4. Run `./mvnw clean package` when you are ready to prepare a `.jar` file for deployment.
