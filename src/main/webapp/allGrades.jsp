<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false"
   import="java.lang.System" %>

<%@ page import="controller.StudentCourses" %>

<%@ page import="controller.StudentGrades" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - AllGrades</title>
</head>
<body>
<h1>Welcome to Grades database
</h1>
<br/>
<% float max = (float) request.getAttribute("max");%>
<% float min = (float) request.getAttribute("min");%>
<% float avg = (float) request.getAttribute("avg");%>
<% float median = (float) request.getAttribute("median");%>
<% String course = (String) request.getAttribute("course");%>

<table>
    <th>Course</th>
    <th>Grade in Class</th>

    <tr>
        <td><%= course %> Max Grade</td>
        <td><%= max %></td>
    </tr>
    <tr>
        <td><%= course %> Min Grade</td>
        <td><%= min %></td>
    </tr>
    <tr>
        <td><%= course %> Avg Grade</td>
        <td><%= avg %></td>
    </tr>
    <tr>
        <td><%= course %> Median Grade</td>
        <td><%= median %></td>
    </tr>
</table>
<hr>

<p>Ola good!!</p>
<!--p><a href="studCourses.jsp">previous</a></p-->
<p><a href="logout.jsp">logout</a></p>
</body>
</html>