<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 13.07.2016
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information</title>
</head>
<body>
    <div align="center">
        <form action="/id" method="get">
            <p>Input ID users which need search :<input type="number" name="id"/></p>
            <input type="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <a href="/"><button>Back</button></a>
    </div>
</body>
</html>
