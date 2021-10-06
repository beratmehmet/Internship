<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: berat
  Date: 3.08.2021
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
<h1>First JSP</h1>
<label>Türkçe Karakterler: ŞşğİıöÖüÜĞ</label>

<label>Today is: <%=Calendar.getInstance().getTime()%> </label>

<a href="first-jsp-servlet">First JSP Servlet</a>
</body>
</html>