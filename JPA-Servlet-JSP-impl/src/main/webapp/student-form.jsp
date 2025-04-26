<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input { padding: 5px; }
    </style>
</head>
<body>
    <h2>${student == null ? 'Add New Student' : 'Edit Student'}</h2>
    <form action="StudentServlet" method="post">
        <input type="hidden" name="action" value="${student == null ? 'insert' : 'update'}">
        <input type="hidden" name="id" value="${student != null ? student.id : ''}">
        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstName" value="${student != null ? student.firstName : ''}" required>
        </div>
        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" value="${student != null ? student.lastName : ''}" required>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" value="${student != null ? student.email : ''}" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Save">
            <a href="StudentServlet">Cancel</a>
        </div>
    </form>
</body>
</html>