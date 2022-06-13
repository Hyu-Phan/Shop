<%-- 
    Document   : show
    Created on : May 14, 2022, 5:04:52 PM
    Author     : pqhuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<form action="/product" method="get">
    Nhập id <input type="text" name="id" placeholder="Mã sản phẩm"> <br>
    <input type="submit" value="Submit">
</form>

<div>
    ${requestScope.msg}
</div>
<div>
    <form action="/item" method="post">
        <c:set var="product" value="${requestScope.product}"></c:set>
        ${product.id}
        ${product.name}
        ${product.price}
        ${product.description}
        <c:forEach items="${product.images}" var="image">
            <img src="images/${image}" alt="alt" width="60px"/>
        </c:forEach>
        <div class="quantity"></div>
        <select class="select-size" name="size">
            <option>Select size</option>
            <c:forEach var="productDetail" items="${product.productDetails}">
                <option class="size">${productDetail.size}</option>
            </c:forEach>
        </select>
        <select class="select-color" name="color">
            <option>Select color</option>
            <c:forEach var="productDetail" items="${product.productDetails}">
                <option class="size">${productDetail.color}</option>
            </c:forEach>
        </select>
        <input name="userId" value="${1}" hidden>
        <input name="productId" value="${product.id}" hidden>
        <input type="number" class="num" value="1" min="1" name="num"/>
        <input type="submit" value="Thêm vào giỏ hàng"/>
    </form>

    <a href="/item?userId=1&productId=${product.id}" class="add-item"> Thêm vào giỏ hàng</a>

    <div>
        <c:forEach var="product" items="${requestScope.relatedProducts}">
            ${product.id}
            ${product.name}
            ${product.price}
            ${product.description}
            <img src="images/${product.images[0]}" alt="alt" width="60px"/>
        </c:forEach>
    </div>

</div>
<script type="text/javascript">
    var selectSize = document.querySelector(".select-size");
    var quantity = document.querySelector(".quantity");
    var addItem = document.querySelector(".add-item");
    var num = document.querySelector(".num");

    var sizeList = []
    <c:forEach var="p" items="${product.productDetails}">
    var detail = {
        "size": '${p.size}',
        "color": '${p.color}',
        "quantity": ${p.quantity}
    }
    sizeList.push(detail)
    </c:forEach>
    console.log(sizeList)
    selectSize.onchange = function () {
        for (let i = 0; i < sizeList.length; i++) {
            if (sizeList[i].size === selectSize.value) {
                quantity.innerHTML = sizeList[i].quantity + "sản phẩm có sẵn";
                // submitBtn.removeAttribute("disabled")

                changePath("size", sizeList[i].size);
                return;
            }
        }
        // submitBtn.setAttribute("disabled", "true")
    }

    num.onchange = function () {
        var numOfProduct = num.value
        changePath("num", numOfProduct)
    }

    function changePath(param, value) {
        var href = addItem.getAttribute("href")
        var index = href.indexOf(param + "=");
        if (index == -1) {
            href += ("&" + param + "=" + value);
        } else {
            var index2 = href.indexOf("&", index);
            var str = '';
            if (index2 == -1) {
                str = href.substring(index)
            } else {
                str = href.substring(index, index2)
            }
            href = href.replace(str, param + "=" + value)
        }
        console.log(href)
        addItem.setAttribute("href", href)
    }

    // submitBtn.onclick = function () {
    //     var form = document.querySelector(".add-form")
    //     var size = document.querySelector(".size").value;
    //     var quantity = document.querySelector(".num").value;
    //     form.action = "item?userId=1";
    //     console.log(form.action)
    //     form.submit();
    // }

</script>
</body>

</html>
