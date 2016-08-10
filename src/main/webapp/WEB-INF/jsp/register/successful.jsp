<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 12.07.2016
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
</head>
<body>
    <div class="user">
        <p><b>User :</b> ${user.id}. ${user.firstName}</p>
        <p><b>Last Name :</b> ${user.lastName}</p>
        <p><b>Password :</b> ${user.password}</p>
        <p><b>Hobby :</b> ${user.hobby}</p><br/>
        <a href="/getAll">Back</a>
    </div>
</body>
</html>
