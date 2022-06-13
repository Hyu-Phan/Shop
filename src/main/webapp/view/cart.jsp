<%--
  Created by IntelliJ IDEA.
  User: pqhuy
  Date: 5/20/2022
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Giỏ hàng của tôi</h1>
<c:set var="items" value="${sessionScope.cart.items}"/>
<table>
    <tr>
        <th>Tên sản phẩm</th>
        <th>Kích cỡ</th>
        <th>Số lượng</th>
        <th>Giá</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>

            <td>${item.product.name}</td>
            <td>${item.size}</td>
            <td>
                <button><a href="/item?id=${item.id}&num=-1">-</a></button>
                    ${item.quantity}
                <button><a href="/item?id=${item.id}&num=1">+</a></button>
            </td>
            <td>${item.money}</td>
            <td><input type="checkbox" value="${item.money}"></td>
            <td><a href="/item?id=${item.id}&num=0">Xóa</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3">Tổng tiền</td>
        <td class="total">0</td>
    </tr>
</table>
<script>
    var selectItem = document.querySelectorAll("input[type = 'checkbox']")
    var totalMoney = document.querySelector(".total")
    selectItem.forEach(function (select) {
        select.onclick = function () {
            if (this.checked == true) {
                totalMoney.innerHTML = eval(totalMoney.innerHTML + "+" + this.value)
            } else {
                totalMoney.innerHTML = eval(totalMoney.innerHTML + "-" + this.value)
            }
        }
    })
</script>
</html>

