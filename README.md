# 🎓 Student Management System API

A secure and scalable RESTful API built with **Spring Boot**, **Spring Security**, **Spring Data JPA**, and **MySQL** for managing student records. The project follows industry-standard layered architecture using Controllers, Services, Repositories, DTOs, and Entity Models.

---

## 🚀 Features

### Student Management
- Create Student
- Get Student by ID
- Get Students by Name
- Get Students by Course
- Update Student Information
- Delete Student
- Manage Student Status

### Security
- Spring Security Authentication
- Protected Endpoints
- Secure User Management

### Architecture
- Controller Layer
- Service Layer
- Repository Layer
- DTO Pattern
- JPA/Hibernate ORM
- Clean Code Structure

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman

---

## 📂 Project Structure

```text
src/main/java/com/example/api

├── config
│   └── SecurityConfig.java
│
├── controller
│   └── StudentController.java
│
├── dtos
│   ├── requestDtos
│   │   └── ReqStudentDto.java
│   │
│   └── responceDtos
│       └── ResStudentDto.java
│
├── model
│   ├── StudentModel.java
│   └── StudentStatus.java
│
├── repository
│   └── Repository.java
│
├── service
│   ├── serviceInterface
│   │   └── ServiceInterface.java
│   │
│   └── serviceLogic
│       └── ServiceLogic.java
│
├── UserModel.java
├── user.java
├── userRepo.java
│
└── ApiApplication.java
```

---

## ⚙️ Getting Started

### Clone the Repository

```bash
git clone https://github.com/PrasanjeetBiswas/pracApi
cd student-management-system
```

### Configure Database

Update your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Create Database

```sql
CREATE DATABASE student_db;
```

### Run the Application

```bash
mvn spring-boot:run
```

or

```bash
mvn clean install
java -jar target/student-management-system.jar
```

---

## 📌 API Endpoints

| Method | Endpoint | Description |
|----------|----------|----------|
| POST | /students | Create Student |
| GET | /students/{id} | Get Student By ID |
| GET | /students/name/{name} | Get Student By Name |
| GET | /students/course/{course} | Get Students By Course |
| PUT | /students/{id} | Update Student |
| DELETE | /students/{id} | Delete Student |

---

## 📥 Sample Request

```json
{
  "name": "John Doe",
  "email": "john@gmail.com",
  "password": "password123",
  "num": "9876543210",
  "status": "ACTIVE",
  "course": "Java Full Stack",
  "batch": "2026"
}
```

---

## 📤 Sample Response

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@gmail.com",
  "course": "Java Full Stack",
  "batch": "2026",
  "status": "ACTIVE"
}
```

---

## 🔐 Security Flow

```text
Client Request
      │
      ▼
Spring Security
      │
      ▼
Authentication
      │
      ▼
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
MySQL Database
```

---

## 🏗 Design Patterns & Concepts Used

- MVC Layered Architecture
- Dependency Injection
- DTO Pattern
- Repository Pattern
- REST API Design
- Spring Security
- JPA Entity Mapping
- Service-Oriented Design

---

## 📈 Future Enhancements

- JWT Authentication
- Role-Based Authorization
- Pagination & Sorting
- Global Exception Handling
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing
- Email Verification

---

## 👨‍💻 Author

**Prasanjeet Biswas**

Aspiring Full Stack Java Developer passionate about building scalable backend applications using Java and Spring Boot.

---

## ⭐ Support

If you found this project useful:

- Star the repository ⭐
- Fork the project 🍴
- Share it with others 🚀
