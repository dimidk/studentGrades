<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Login Page" %>
</h1>
<br/>

<p><h1>Grades Database.</h1>
<h3>Login in to see your Courses and Grades</h3>
</p>
<hr>
<form action="login-servlet" method="post">
    <label>Student</label>
    <input type = "text" id = "studentId" name = "studentId" value="test"></input><br><br>
    <!--label id = "passwdLabel">Password</label>
    <input type = "password" id="passwdId" name = "passwdId" value="test"></input --><br><br>
        <input type="submit" value="Submit" />
</form>
</body>
</html>