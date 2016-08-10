<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 30.07.2016
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Products</title>
</head>
<body>
    <ul>
        <c:forEach var="product" items="${productList}">
            <li>
                <a href="./searchProduct?id=${product.id}">${product.name}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
