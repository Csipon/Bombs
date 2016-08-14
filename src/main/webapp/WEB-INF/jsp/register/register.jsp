<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 12.07.2016
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div align="center">
        <h1>Registration</h1>
        <form action="/addUser" method="post">
            Nick (2-10) : <input type="text" name="nick"/></p>
            First name(2-16) : <input type="text" name="firstName"/></p>
            Last name(2-16) : <input type="text" name="lastName"/></p>
            Password(6-16) : <input type="text" name="password"/></p>
            About(< 255) : <input type="text" name="about"/></p>
            E-mail : <input type="text" name="email"/></p>
            <input type="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <a href="/checkLogin"><button>Back</button></a>
    </div>
</body>
</html>
