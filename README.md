# Java Spring Boot Employee CRUD

Employee Management CRUD application built using **Java, Spring Boot, Spring Data JPA, and MySQL**.

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* REST API

## Features

* Create employee
* Get employee details
* Update employee
* Delete employee
* RESTful API implementation
* MySQL database integration
* Layered architecture using Controller, Service, Repository, Entity, and DTO

## Project Structure

```text
src/main/java/com/example/employeecrud
│
├── controller
│   └── EmployeeController.java
│
├── dto
│   └── EmployeeDTO.java
│
├── entity
│   └── Employee.java
│
├── repository
│   └── EmployeeRepository.java
│
├── service
│   ├── EmployeeService.java
│   └── EmployeeServiceImpl.java
│
└── EmployeeCrudApplication.java
```

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Configure the MySQL database in `application.properties`.
4. Run `EmployeeCrudApplication.java`.
5. Test the REST APIs using Postman.

## Database

The application uses **MySQL** with **Spring Data JPA** for database operations.

## Project Purpose

This project was created for practicing and understanding **Java, Spring Boot, REST APIs, JPA, MySQL, and layered application architecture**.
