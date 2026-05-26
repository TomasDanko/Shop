# Shop – Spring Boot Backend Application

## 📌 Project Overview

Shop is a backend REST API built with Spring Boot.  
It provides basic e-commerce functionality for managing customers, merchants, and products.  
The application uses PostgreSQL as the database and JdbcTemplate for data access.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3.5.7
- Spring Web
- Spring JDBC
- Spring Data JPA
- PostgreSQL
- WebSocket
- JUnit (testing)

---

## 🏗️ Architecture

The project follows a layered architecture:

- **Controller Layer** – REST endpoints
- **Service Layer** – business logic
- **Repository Layer** – database access using JdbcTemplate
- **Domain Layer** – POJO models (Customer, Product, Merchant)

---

## 📦 Main Entities

### Customer
- id
- name, surname
- email
- address
- age
- phone number

### Merchant
- id
- name
- email
- address

### Product
- id
- merchantId
- name
- description
- price
- createdAt
- available

---

## 🌐 REST API Endpoints

### Customer API
- `GET /customer/{id}` – get customer by ID
- `GET /customer` – get all customers
- `POST /customer` – create customer

### Merchant API
- `GET /merchant/{id}` – get merchant by ID
- `GET /merchant` – get all merchants
- `POST /merchant` – create merchant

### Product API
- `GET /product/{id}` – get product by ID
- `GET /product` – get all products
- `POST /product` – create product
- `PATCH /product/{id}` – update product
- `DELETE /product/{id}` – delete product

---

## 🔌 WebSocket Support

The application includes WebSocket configuration using STOMP:

- Endpoint: `/ws`
- Broker: `/topic`
- Application prefix: `/app`

Used for real-time communication.

---

## 🗄️ Database Configuration

Configured in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres_danko_shop
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
