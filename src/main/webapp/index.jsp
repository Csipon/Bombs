<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 12.07.2016
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <h1>Registration</h1>
    <form action="/log" method="post">
        Name : <input type="text" name="name" placeholder="Username..."/>
        Password : <input type="password" name="password" placeholder="Password..."/>
        <br/><br/>
        <input type="submit" value="SUBMIT">
        <input type="reset" value="REST">
    </form>
    <a href="<c:url value="/register" />"><button>Register</button></a>
</div>
</body>
</html>