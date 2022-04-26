<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false"
   import="java.lang.System" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="controller.StudentCourses" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.StudentGrades" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Result</title>
</head>
<body>
<h1>Welcome to Grades database
</h1>
<br/>
<% String name = (String) request.getAttribute("name");%>

<h4><%=name%> these are your chosen courses</h4>
<% List<StudentCourses> studCourses = (List<StudentCourses>) request.getAttribute("studCourses");%>
<% List<StudentGrades> studGrades = (List<StudentGrades>) request.getAttribute("studGrades");%>

<table>
    <th>Course</th>
    <%
        for(int i=0; i<studCourses.size(); i++){%>
    <tr>
        <td><%= (studCourses.get(i)).getCourseName() %></td>
    </tr>
    <%}%>
</table>
<hr>
Your grades in your courses are:
<table>
    <th>Course</th>
    <th>Grade</th>
    <%
        for(int i=0; i<studGrades.size(); i++){%>
    <tr>
        <td><%= (studGrades.get(i)).getCourseName() %></td>
        <td><%= (studGrades.get(i)).getGrade() %></td>
    </tr>
    <%}%>
</table>
<hr>
In order to see the max, min , avg of a course, please give the name:
<form action="data-servlet" method="get">
    Give course name for grades' information:
    <input type="text" value="" name="course" />
    <input type="submit" value="Submit" />
</form>
<p>Ola good!!</p>
</body>
</html>