# Java Spring Boot Angular Employee Management System

Full-stack Employee Management System built with **Java, Spring Boot, Angular, Spring Data JPA, and MySQL**.

This project demonstrates a complete full-stack CRUD application where the Angular frontend communicates with a Spring Boot REST API for managing employee information.

## Technologies Used

### Backend

- Java 17
- Spring Boot 3.5.5
- Spring Data JPA
- MySQL 8
- Maven
- REST API
- Jakarta Validation
- JUnit
- Mockito

### Frontend

- Angular 21
- TypeScript
- HTML5
- CSS3
- Angular Reactive Forms
- Angular HttpClient

## Features

### Employee Management

- Create employee
- View employee details
- Update employee
- Delete employee
- Search employees
- Client-side pagination
- Delete confirmation dialog
- Success notifications
- Responsive user interface

### Backend Features

- RESTful API implementation
- MySQL database integration
- Spring Data JPA
- Layered architecture
- Controller, Service, Repository, Entity, and DTO layers
- DTO-based request and response handling
- Input validation using Jakarta Validation
- Global exception handling
- CORS configuration for Angular frontend
- Unit testing using JUnit and Mockito

### Frontend Features

- Angular feature-based architecture
- Employee list
- Add employee form
- Edit employee form
- Reactive form validation
- Search by employee ID
- Search by employee name
- Search by email
- Search by phone number
- Client-side pagination
- Delete confirmation modal
- Success toast notifications
- Responsive design

## Application Architecture

┌─────────────────────────────────────┐
│          Angular Frontend           │
│                                     │
│  Employee List                      │
│  Add / Edit Employee                │
│  Search & Pagination                │
│  Reactive Form Validation           │
│  Delete Confirmation                │
└──────────────────┬──────────────────┘
                   │
                   │ HTTP / REST API
                   ▼
┌─────────────────────────────────────┐
│         Spring Boot Backend         │
│                                     │
│  Controller                         │
│       ↓                             │
│  Service                            │
│       ↓                             │
│  Repository                         │
│       ↓                             │
│  Spring Data JPA                    │
└──────────────────┬──────────────────┘
                   │
                   ▼
┌─────────────────────────────────────┐
│            MySQL Database           │
└─────────────────────────────────────┘

## Project Structure

java-springboot-angular-employee-management-system

├── .gitignore
├── .gitattributes
├── README.md
│
├── backend-springboot
│   │
│   ├── .mvn
│   │   └── wrapper
│   │       └── maven-wrapper.properties
│   │
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.example.employeecrud
│   │   │   │       │
│   │   │   │       ├── controller
│   │   │   │       │   └── EmployeeController.java
│   │   │   │       │
│   │   │   │       ├── dto
│   │   │   │       │   ├── ApiResponse.java
│   │   │   │       │   └── EmployeeDTO.java
│   │   │   │       │
│   │   │   │       ├── entity
│   │   │   │       │   └── Employee.java
│   │   │   │       │
│   │   │   │       ├── exception
│   │   │   │       │   └── GlobalExceptionHandler.java
│   │   │   │       │
│   │   │   │       ├── repository
│   │   │   │       │   └── EmployeeRepository.java
│   │   │   │       │
│   │   │   │       ├── service
│   │   │   │       │   ├── EmployeeService.java
│   │   │   │       │   └── EmployeeServiceImpl.java
│   │   │   │       │
│   │   │   │       └── EmployeeCrudApplication.java
│   │   │   │
│   │   │   └── resources
│   │   │       └── application.properties
│   │   │
│   │   └── test
│   │       └── java
│   │           └── com.example.employeecrud
│   │               │
│   │               ├── controller
│   │               │   └── EmployeeControllerTest.java
│   │               │
│   │               └── service
│   │                   └── EmployeeServiceImplTest.java
│   │
│   ├── HELP.md
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
└── frontend-angular
    │
    └── employee-ui
        │
        ├── .editorconfig
        ├── .prettierrc
        ├── angular.json
        ├── package.json
        ├── package-lock.json
        │
        ├── public
        │   └── favicon.ico
        │
        ├── src
        │   ├── app
        │   │   │
        │   │   ├── employee
        │   │   │   ├── models
        │   │   │   │   ├── employee.ts
        │   │   │   │   └── api-response.ts
        │   │   │   │
        │   │   │   ├── services
        │   │   │   │   ├── employee.ts
        │   │   │   │   └── employee.spec.ts
        │   │   │   │
        │   │   │   ├── employee-form
        │   │   │   │   ├── employee-form.ts
        │   │   │   │   ├── employee-form.html
        │   │   │   │   └── employee-form.css
        │   │   │   │
        │   │   │   ├── employee.ts
        │   │   │   ├── employee.html
        │   │   │   ├── employee.css
        │   │   │   └── employee.spec.ts
        │   │   │
        │   │   ├── app.ts
        │   │   ├── app.html
        │   │   ├── app.css
        │   │   ├── app.config.ts
        │   │   └── app.routes.ts
        │   │
        │   ├── main.ts
        │   ├── index.html
        │   └── styles.css
        │
        ├── tsconfig.app.json
        ├── tsconfig.json
        └── tsconfig.spec.json

## Backend API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /employee/getEmployee | Get all employees |
| POST | /employee/saveEmployee | Create a new employee |
| PUT | /employee/updateEmployee/{id} | Update an existing employee |
| DELETE | /employee/deleteEmployee/{id} | Delete an employee |

## Employee Fields

| Field | Type | Description |
|-------|------|-------------|
| ID | String | Unique employee identifier |
| Name | String | Employee name |
| Email | String | Employee email address |
| Phone Number | String | 10-digit Indian mobile number |
| Salary | Number | Employee salary |

## Backend Validation

Employee request data is validated using **Jakarta Validation**.

The backend validates:

- Employee ID is required
- Employee ID must contain numbers only
- Employee name is required
- Employee name must contain letters and spaces
- Email is required
- Email must be valid
- Phone number is required
- Phone number must be a valid 10-digit Indian mobile number
- Salary is required
- Salary must be greater than zero

Invalid requests are handled through the global exception handler.

## Frontend Validation

The Angular application uses **Reactive Forms** for client-side validation.

The frontend validates:

- Required fields
- Employee ID format
- Employee name format
- Email format
- Indian mobile number format
- Salary value

Validation messages are displayed directly on the employee form.

## Database

The application uses **MySQL** with **Spring Data JPA**.

Create the database before running the backend:

CREATE DATABASE employeedb;

Database configuration:

- Database: employeedb
- Host: localhost
- Port: 3306
- Username: root

The database password is supplied through the `DB_PASSWORD` environment variable and is not stored directly in source control.

## How to Run

### Prerequisites

Make sure the following are installed:

- Java 17 or later
- Maven
- MySQL 8 or later
- Node.js
- npm
- Angular CLI

### 1. Clone the Repository

Clone the repository using:

git clone https://github.com/DileepAdluri/java-springboot-angular-employee-management-system.git

Navigate to the project:

cd java-springboot-angular-employee-management-system

### 2. Create MySQL Database

Start MySQL and create the `employeedb` database.

### 3. Configure Database Password

The backend reads the database password from the `DB_PASSWORD` environment variable.

For Windows PowerShell:

$env:DB_PASSWORD="your-password"

### 4. Run Spring Boot Backend

Navigate to the backend:

cd backend-springboot

Run the Spring Boot application:

mvn spring-boot:run

The backend will start at:

http://localhost:8080

### 5. Run Angular Frontend

Open another terminal and navigate to:

cd frontend-angular/employee-ui

Install Angular dependencies:

npm install

Start the Angular development server:

ng serve

The frontend will be available at:

http://localhost:4200

## Application Flow

User → Angular Employee Management UI → Spring Boot REST API → Employee Service → Employee Repository → MySQL Database → REST API Response → Angular UI

## CRUD Operations

### Create Employee

The Angular application sends employee information to the Spring Boot backend using:

POST /employee/saveEmployee

The backend validates the request and stores the employee in MySQL.

### Get Employees

The Angular application retrieves employees using:

GET /employee/getEmployee

The employee list is displayed in the Angular UI.

### Update Employee

When an employee is edited, the Angular application sends:

PUT /employee/updateEmployee/{id}

The backend updates the corresponding employee record.

### Delete Employee

When deleting an employee, the Angular application displays a confirmation dialog.

After confirmation, the Angular application sends:

DELETE /employee/deleteEmployee/{id}

The employee is removed from the database and the updated employee list is displayed.

## Search and Pagination

The employee list provides client-side search functionality.

Users can search employees by:

- Employee ID
- Name
- Email
- Phone number

The employee list also supports client-side pagination.

## Error Handling

The backend uses a global exception handler to handle validation and application errors.

Validation errors are returned as structured API responses.

Example API response:

{
  "message": "Validation failed",
  "data": {
    "name": "Name is required",
    "email": "Please enter a valid email address"
  }
}

## API Response Format

The backend uses a common API response structure.

Example:

{
  "message": "Employees fetched successfully",
  "data": []
}

The response contains:

- `message` - Operation status message
- `data` - Response data

## Testing

### Backend Testing

The backend uses:

- JUnit
- Mockito
- Spring Boot Test

Unit tests cover the Controller and Service layers.

Run backend tests using:

cd backend-springboot

mvn test

### Frontend Testing

Angular test configuration is included in the frontend application.

Run frontend tests using:

cd frontend-angular/employee-ui

ng test

## Security and Configuration

Database credentials are not hard-coded in the application configuration.

The database password is supplied through the `DB_PASSWORD` environment variable.

Local configuration files and sensitive values are excluded from source control through `.gitignore`.

## GitHub Repository Structure

The repository is organized as a full-stack project with separate backend and frontend applications.

- `backend-springboot` - Spring Boot REST API
- `frontend-angular/employee-ui` - Angular Employee Management UI
- `.gitignore` - Repository-level Git ignore configuration
- `.gitattributes` - Git attributes configuration
- `README.md` - Project documentation

This structure keeps the frontend and backend separated while maintaining both applications inside a single repository.

## Project Purpose

This project was created to demonstrate a complete **full-stack Employee Management System** using Java, Spring Boot, Angular, and MySQL.

The project demonstrates practical implementation of:

- Java
- Spring Boot
- REST APIs
- Spring Data JPA
- MySQL
- Angular
- TypeScript
- Reactive Forms
- CRUD operations
- Frontend and backend integration
- Input validation
- Exception handling
- Unit testing
- Feature-based Angular architecture
- Layered Spring Boot architecture
- Environment-based configuration

## Future Enhancements

- Authentication and authorization
- Login and registration
- Role-based access control
- Employee dashboard
- Advanced employee filtering
- Server-side pagination
- Sorting
- Employee profile management
- Docker support
- Production deployment
- CI/CD integration

## Author

**Dileep Adluri**