# Genpact Bank Application Web Project

## Overview
The **Genpact Bank Application** is a web-based banking application developed using **Java**, **Spring Boot**, **MySQL**, and **JSP**. It provides core banking functionalities, including account creation, management, and transaction handling, and enables customers to generate downloadable PDFs of transaction history.

---

## Features
- User authentication and role-based access control.
- Account creation and management.
- Deposit, withdrawal, and account balance inquiry.
- Transaction history view with downloadable PDFs.
- JSP views for user-friendly interfaces.

---

## Prerequisites
To run this project, ensure you have the following installed:
- **Java 17** or higher
- **Apache Maven** (for dependency management)
- **MySQL Server**
- **Spring Boot 3.3.3**
- **Tomcat Server**

---

## Getting Started

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd Genpact_Bank_Application_Web_Project
```

### Step 2: Setup MySQL Database
1. **Install MySQL Server** (if not already installed).  
   You can download it from the [MySQL official website](https://dev.mysql.com/downloads/).

2. **Create a new MySQL database**:
   ```sql
   CREATE DATABASE banking_app;
   ```

3. **Create a MySQL user with credentials**:
   ```sql
   CREATE USER 'bank_user'@'localhost' IDENTIFIED BY 'securepassword';
   GRANT ALL PRIVILEGES ON banking_app.* TO 'bank_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. Update the **`application.properties`** file with your MySQL connection details.

### Step 3: Configure `application.properties`
Create or modify the `src/main/resources/application.properties` file with the following content:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
spring.datasource.username=bank_user
spring.datasource.password=securepassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
```

### Step 4: Build and Run the Application
1. **Build the project**:
   ```bash
   mvn clean install
   ```

2. **Run the project**:
   ```bash
   mvn spring-boot:run
   ```

3. Access the application at: [http://localhost:8080](http://localhost:8080)

---

## Dependencies
The project uses the following major dependencies:
- **Spring Boot Starter Web**: For building RESTful APIs and web applications.
- **Spring Boot Starter Data JPA**: For ORM and database interaction.
- **MySQL Connector**: For MySQL database connection.
- **iTextPDF**: For generating PDF transaction reports.
- **JSP and JSTL**: For server-side rendering of views.
- **Lombok**: To reduce boilerplate code.
- **Tomcat Embed Jasper**: For JSP support in Spring Boot.

---

## Folder Structure
```
src/
├── main/
│   ├── java/com/genpact/web/       # Java source code
│   ├── resources/
│   │   ├── application.properties  # Configuration file
│   ├── webapp/WEB-INF/jsp          # JSP files
└── test/                           # Test cases
```

---

## Database Schema
The database includes the following tables:
1. **Users**: Stores user details like username, password, and role.
2. **Accounts**: Tracks account details like balance and account status.
3. **Transactions**: Logs deposits, withdrawals, and transfers.

---

## How to Contribute
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m "Description of changes"`
4. Push to the branch: `git push origin feature-name`
5. Open a Pull Request.

---
