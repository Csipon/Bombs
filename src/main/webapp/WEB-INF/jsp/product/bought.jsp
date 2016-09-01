<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 14.08.2016
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Bought products</title>
</head>
<body>
    <div align="center">
        <h2>Thanks for bought products in my shop</h2>

            <c:forEach var="prod" items="${boughtProducts}" >
                ${prod.key.name} : ${prod.value}
            </c:forEach>
        <h3>$ ${priceBought}</h3>
        <a href="/checkLogin"><button>Back</button></a>
    </div>
</body>
</html>
