<%--
  Created by IntelliJ IDEA.
  User: berat
  Date: 5.08.2021
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<h2>Search:</h2>
<form action="editContact-servlet", method="GET">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <input type="submit" value="Submit">
</form>


</body>
</html>
