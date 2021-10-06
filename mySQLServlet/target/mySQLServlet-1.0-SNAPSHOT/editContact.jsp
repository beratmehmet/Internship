<%--
  Created by IntelliJ IDEA.
  User: berat
  Date: 5.08.2021
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
<h1>Edit:</h1>
Name: ${name}<br>
Phone number: ${tel_number}
<br>
<form action="editContact-servlet" method="POST">
    <label for="tel_number">Edit Phone Number:</label><br>
    <input type="text" id="tel_number" name="tel_number"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
