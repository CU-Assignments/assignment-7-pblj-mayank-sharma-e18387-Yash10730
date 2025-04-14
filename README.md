[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NkCpgtOH)
# 🌐 Java Web Applications using Servlets and JSP

This repository demonstrates how to build web applications using **Servlets and JSP**, with increasing levels of difficulty – from basic user login to a dynamic student attendance portal with database integration.

---

## 📌 Table of Contents

- [📗 Easy Level: User Login with Servlet](#-easy-level-user-login-with-servlet)
- [📙 Medium Level: Employee Directory with JDBC](#-medium-level-employee-directory-with-jdbc)
- [📕 Hard Level: JSP-Based Student Attendance Portal](#-hard-level-jsp-based-student-attendance-portal)
- [🛠️ Tech Stack](#️-tech-stack)
- [🚀 Getting Started](#-getting-started)
- [📦 Folder Structure](#-folder-structure)
- [📧 Contact](#-contact)

---

## 📗 Easy Level: User Login with Servlet

🔐 A simple servlet-based login system that accepts user credentials via an HTML form and displays a personalized welcome message if login is successful.

### ✅ Features:
- HTML form to collect username and password
- Servlet to validate user input (static check for now)
- Displays a welcome message upon successful login

### 📁 Files:
- `login.html`
- `LoginServlet.java`
- `welcome.jsp`

### 🔄 Flow:
1. User submits login form.
2. `LoginServlet` checks credentials.
3. If valid, user is forwarded to `welcome.jsp`.

---

## 📙 Medium Level: Employee Directory with JDBC

🗂️ A servlet application that connects to a **database using JDBC** to fetch and display a list of employees. Includes a search option to retrieve an employee by ID.

### ✅ Features:
- Displays list of all employees from the database
- Search employee by entering their ID
- Uses JDBC for database communication

### 📁 Files:
- `employeeList.html`
- `EmployeeServlet.java`
- `db-config.properties`

### 🧪 Sample SQL Table:
```sql
CREATE TABLE employees (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  department VARCHAR(100),
  email VARCHAR(100)
);
📕 Hard Level: JSP-Based Student Attendance Portal
📊 A JSP-based form for collecting attendance details and saving them to the database through a servlet.

✅ Features:
JSP form to enter attendance info

Servlet handles form submission and stores data

Data is persisted to the database

📁 Files:
attendance.jsp

AttendanceServlet.java

StudentAttendance.java

attendance-success.jsp
