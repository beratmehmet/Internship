<%--
  Created by IntelliJ IDEA.
  User: berat
  Date: 4.08.2021
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>This is main.jsp</h1>

<jsp:include page="header.jsp" flush="true">
    <jsp:param name="name" value="berat" />
</jsp:include>

<jsp:include page="header.jsp" flush="true"/>


<%@include file="header.jsp" %>

<jsp:include page="footer.jsp"/>

</body>
</html>
