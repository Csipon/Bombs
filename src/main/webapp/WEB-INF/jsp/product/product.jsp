<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 30.07.2016
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/product.css" />" rel="stylesheet">
    <title>Product</title>
</head>
<body>
    <div class="prod">

        <div class="info">
            <p><b>Product :</b> ${product.id}. ${product.name}</p>
            <p><b>Price :</b> ${product.price}</p>
            <p><b>About :</b> ${product.about}</p>
            <div class="productImg">
                <img src="../../../images/product/${product.name.toLowerCase()}.jpg">
            </div>
            <br/><a href="<c:url value="/products" />">Back</a>
        </div>
        <br/>
        <a href="./addToBucket?id=${product.id}">Add this product to bucket</a>

        <div>
            <h3>Your product bucket</h3>

            <ul>
                <c:forEach var="prod" items="${productInBucket}" >
                    <li>
                        <a href="./searchProduct?id=${prod.key.id}">${prod.key.name}</a> : ${prod.value} |
                        <a href="./cleanBucket?id=${prod.key.id}"><img src="../../../images/delete.png"></a>
                    </li>
                </c:forEach>
            </ul>
            <h3>${price}</h3> <a href="<c:url value="/buy"/>"><button>Buy product</button></a>
        </div>
    </div>
</body>
</html>
