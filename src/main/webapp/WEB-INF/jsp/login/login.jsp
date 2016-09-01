<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 01.08.2016
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
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
        <a href="/"><button>Back</button></a>
    </div>
</body>
</html>
