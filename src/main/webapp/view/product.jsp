<%-- 
    Document   : product
    Created on : May 8, 2022, 6:38:17 PM
    Author     : pqhuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <title>JSP Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3>Thêm sản phẩm vào kho</h3>--%>
<%--<form method="post" action="add" enctype="multipart/form-data">--%>
<%--    Mã <input type="text" name="id" placeholder="Mã"/><br/>--%>
<%--    Tên sản phẩm <input type="text" name="name" placeholder="Tên"/><br/>--%>
<%--    Giá <input type="text" placeholder="Giá" name="price"/><br/>--%>
<%--    Chi tiết sản phẩm--%>
<%--    <br>--%>
<%--    <input type="checkbox" id="size-S" value="s" name="size"/>--%>
<%--    <label for="size-S">S</label>--%>
<%--    Số lượng <input type="number" name="quantity_size_s">--%>
<%--    <br>--%>
<%--    <input type="checkbox" id="size-M" value="m" name="size"/>--%>
<%--    <label for="size-M">M</label>--%>
<%--    Số lượng <input type="number" name="quantity_size_m" min="0" value="0">--%>
<%--    <br>--%>
<%--    <input type="checkbox" id="size-L" value="l" name="size"/>--%>
<%--    <label for="size-L">L</label>--%>
<%--    Số lượng <input type="number" name="quantity_size_l" min="0" value="0">--%>
<%--    <br>--%>
<%--    <input type="checkbox" id="size-XL" value="xl" name="size"/>--%>
<%--    <label for="size-XL">XL</label>--%>
<%--    Số lượng <input type="number" name="quantity_size_xl">--%>
<%--    <br>--%>
<%--    <button class="them" value="Thêm"></button>--%>
<%--    Ảnh <input type="file" name="file" size="60"/><br/>--%>
<%--    <input type="submit" value="Thêm sản phẩm"/>--%>
<%--</form>--%>
<%--<div>--%>
<%--    ${requestScope.msg}--%>
<%--</div>--%>

<%--<script>--%>

<%--</script>--%>
<%--</body>--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        .them {
            border: 1px solid;
            padding: 8px;
            border-radius: 3px;
            width: 40px;
            text-align: center;
            cursor: pointer;
            margin: 8px;
            background-color: #ccc;
        }

        .detail div {
            margin: 8px
        }
    </style>
</head>
<body>
<h3>Thêm sản phẩm vào kho</h3>
<form method="get" action="add" enctype="application/x-www-form-urlencoded">
    <div>
        Mã <input type="text" name="id" placeholder="Mã"/>
    </div>
    <div>
        Tên sản phẩm <input type="text" name="name" placeholder="Tên"/>
    </div>
    <div>
        Giá <input type="text" placeholder="Giá" name="price"/>
    </div>

    <div class="detail">
        Chi tiết sản phẩm
        <div>
            Size <input type="text" name="size"/>
            Màu <input type="text" name="color"/>
            Số lượng <input type="number" name="quantity">

        </div>
    </div>

    <div class="them">Thêm</div>
    Ảnh <input type="file" name="file" size="60"/><br/>
    <input type="submit" value="Thêm sản phẩm"/>
</form>
<div>

    <script>
        var btn = document.querySelector(".them")
        var detail = document.querySelector(".detail")
        var inputs = document.querySelectorAll("input")
        inputs.forEach(function (input) {
            input.onchange = function () {
                input.setAttribute("value", input.value)
            }
        })
        btn.onclick = function () {
            detail.innerHTML = detail.innerHTML + `
            <div>
                Size <input type="text" name="size"/>
                Màu <input type="text" name="color"/>
                Số lượng <input type="number" name="quantity">
            </div>
        `
            inputs = document.querySelectorAll("input")
            inputs.forEach(function (input) {
                input.onchange = function () {
                    input.setAttribute("value", input.value)
                }
            })
        }
    </script>
</html>
