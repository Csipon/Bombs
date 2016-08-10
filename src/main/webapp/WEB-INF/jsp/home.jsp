<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 10.08.2016
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<div align="center">
    <h1>Welcome </h1>
    <a href="<c:url value="/register" />"><button>Register</button></a> |
    <a href="<c:url value="/signIn" />"><button>Sign in</button></a> |
    <a href="<c:url value="/information"/>"><button>Information</button></a> |
    <a href="<c:url value="/to.mail"/>"><button>Send e-mail</button></a> |
    <a href="<c:url value="/products"/>"><button>Products</button></a> |
    <a href="<c:url value="/getAll" />"><button>Users</button></a><br><br>

    <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
    <form action="/time" method="POST">
        <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
        <input type="submit" value="Counting time"/>
    </form>
</div>
</body>
</html>
