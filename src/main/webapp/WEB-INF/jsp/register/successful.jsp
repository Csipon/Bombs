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
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Successful</title>
</head>
<body>
    <div class="user">
        <p><b>User :</b> ${user.id}. ${user.nick}</p>
        <p><b>Last Name :</b> ${user.lastName}</p>
        <p><b>Password :</b> ${user.password}</p>
        <p><b>About :</b> ${user.about}</p>
        <p><b>E-mail :</b> ${user.email}</p>
        <p><b>About :</b> ${user.about}</p><br/>
        <a href="/getAll">Back</a>
    </div>
</body>
</html>
