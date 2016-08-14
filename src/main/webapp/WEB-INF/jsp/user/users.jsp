<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 14.07.2016
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <div align="center">
            <a href="/id?id=${pers.id}">${pers.firstName} ${pers.lastName}</a>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>First name</th>
                <th>Last name</th>
                <th>About</th>
                <th>E-mail</th>
                <th>Password</th>
            </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <th>${user.id}</th>
                <th><a href="/id?id=${user.id}">${user.nick}</a></th>
                <th>${user.firstName}</th>
                <th>${user.lastName}</th>
                <th>${user.about}</th>
                <th>${user.email}</th>
                <th><c:forEach begin="1" step="1" end="${user.password.length()}">*</c:forEach></th>
                <th><a href="/delete?id=${user.id}&password=${user.password}"><img src="../../../images/delete.png"></a></th>
            </tr>
        </c:forEach>
        </table>
        <br/>
        <a href="/checkLogin"><button>Back</button></a>
    </div>
</body>
</html>
