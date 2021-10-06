<%--
  Created by IntelliJ IDEA.
  User: berat
  Date: 5.08.2021
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Contact</title>
</head>
<body>
<h2>Create Contact</h2>
<form action="createContact-servlet", method="POST">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="tel_number">Phone Number:</label><br>
    <input type="text" id="tel_number" name="tel_number"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
