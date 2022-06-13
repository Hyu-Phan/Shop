<%--
  Created by IntelliJ IDEA.
  User: pqhuy
  Date: 6/12/2022
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="../css/product.css">
</head>
<body>
<h3>Thêm sản phẩm vào kho</h3>
<form method="post" action="/edit-product" enctype="application/x-www-form-urlencoded">
    <c:set var="p" value="${requestScope.product}"/>
    <div>
        Mã <input type="text" name="id" placeholder="Mã" value="${p.id}" readonly/>
    </div>
    <div>
        Tên sản phẩm <input type="text" name="name" placeholder="Tên" value="${p.name}"/>
    </div>
    <div>
        Giá <input type="text" placeholder="Giá" name="price" value="${p.price}"/>
    </div>
    <div>
        Mô tả <input type="text" placeholder="Mô tả" name="description" value="${p.description}"/>
    </div>

    <div class="detail">
        Chi tiết sản phẩm
        <c:forEach var="details" items="${p.productDetails}">
            <div>
                Size <input type="text" name="size" value="${details.size}"/>
                Màu <input type="text" name="color" value="${details.color}"/>
                Số lượng <input type="number" name="quantity" value="${details.quantity}"/>
                <input type="text" name="detail" value="${details.id}" hidden/>
                <div class="xoa"> Xóa</div>
            </div>
        </c:forEach>
    </div>

    <div class="them">Thêm</div>

    <c:forEach var="image" items="${p.images}">
        <img src="images/${image}">
    </c:forEach>
    Ảnh <input type="file" name="file" size="60"/><br/>
    <input type="submit" value="Sửa đổi thông tin"/>
</form>
<div>

    <script src="/js/product.js">

    </script>
</html>
