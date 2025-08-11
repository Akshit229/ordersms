

## Setup & Run Instructions

### Prerequisites

Before running the application, ensure you have:

* **Java 17+** installed
* **Maven 3+** installed
* **MySQL 8+** installed and running on port `3306`.
* **MySQL Workbench** or **phpMyAdmin** installed for managing the database.
* **Flyway** for database migration
* **Lombok** for reducing boilerplate code
* **MapStruct** for DTO-Entity mapping
* **Swagger/OpenAPI** for API documentation


---

### Clone the Repository

```bash
git clone https://github.com/Akshit229/ordersms.git
```

---

### Database Setup

* By default, the application connects to a MySQL database named **`oms_dev`**.
* Flyway will automatically create the database schema, tables, and insert dummy data during startup.
* **If startup fails due to database not found**, create it manually:

```sql
CREATE DATABASE oms_dev;
```

* To use a custom database name, update `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/<your-db-name>?createDatabaseIfNotExist=true
    username: root
    password: root
```

---

### Run the Application

#### Using Maven:

```bash
mvn spring-boot:run
````

#### Or Build and Run JAR:

```bash
mvn clean package -DskipTests
java -jar target/ordersms-0.0.1-SNAPSHOT.jar
```

---

### Access the Application

* API Base URL:

  ```
  http://localhost:8080
  ```
* Swagger UI:

  ```
  http://localhost:8080/swagger-ui.html
  ```

---

### Controlling Async Delay

The async delay (in milliseconds) can be configured in `application.yml`:

```yaml
app:
  delay-ms: 3000
```

---

### Development Considerations

* For this assignment, the **`V2__seed_for_orders_and_products_table`** Flyway migration script (located in `src/main/resources/db/migration`) is used to seed dummy data into the `orders` and `products` tables.
* **Lombok** is used to eliminate boilerplate code such as constructors, getters, and setters where applicable. In some cases, Lombok is deliberately not used due to compatibility considerations.
* **MapStruct** mappers are used to convert between DTOs and entities.
* **Global exception handling** is implemented using a centralized error handler instead of method-level exception handling.
