// Assignment-7 Code Files

// login.html
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        Username: <input type="text" name="username" /><br/>
        Password: <input type="password" name="password" /><br/>
        <input type="submit" value="Login" />
    </form>
</body>
</html>

// LoginServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin123".equals(password)) {
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            dispatcher.forward(request, response);
        } else {
            response.getWriter().println("Invalid username or password");
        }
    }
}

// welcome.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, ${username}!</h2>
</body>
</html>

// employeeList.html
<!DOCTYPE html>
<html>
<head>
    <title>Employee Directory</title>
</head>
<body>
    <form action="EmployeeServlet" method="get">
        Search by ID: <input type="text" name="id" />
        <input type="submit" value="Search" />
    </form>
</body>
</html>

// EmployeeServlet.java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<String> employees = new ArrayList<>();

        try {
            Properties props = new Properties();
            props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = id == null || id.isEmpty() ? stmt.executeQuery("SELECT * FROM employees") : stmt.executeQuery("SELECT * FROM employees WHERE id=" + id);

            while (rs.next()) {
                employees.add("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Dept: " + rs.getString("department") + ", Email: " + rs.getString("email"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        for (String emp : employees) {
            out.println("<p>" + emp + "</p>");
        }
    }
}

// db-config.properties
url=jdbc:mysql://localhost:3306/your_database
user=root
password=your_password

// attendance.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <form action="AttendanceServlet" method="post">
        Student Name: <input type="text" name="name" /><br/>
        Date: <input type="date" name="date" /><br/>
        Present (true/false): <input type="text" name="present" /><br/>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>

// AttendanceServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        boolean present = Boolean.parseBoolean(request.getParameter("present"));

        try {
            Properties props = new Properties();
            props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));
            Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendance (name, date, present) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setBoolean(3, present);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("attendance-success.jsp");
    }
}

// attendance-success.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Success</title>
</head>
<body>
    <h2>Attendance Recorded Successfully!</h2>
</body>
</html>
