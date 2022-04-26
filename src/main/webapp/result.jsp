<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
   import="java.lang.System" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Result</title>
</head>
<body>
<h1>Result
</h1>
<br/>
<% String name = (String) request.getAttribute("name");
   //String welcome = (String) request.getAttribute("welcome");
   // System.out.println(welcome + " "+name);
    %>
<%=name%>
<p>Ola good!!</p>
</body>
</html>