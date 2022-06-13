<%--
  Created by IntelliJ IDEA.
  User: pqhuy
  Date: 5/28/2022
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LapStore - Giỏ hàng</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<!-- ------------Menu----------- -->
<div class="noi-dung">
    <div class="dieu-huong">
        <div class="logo">
            <a href="index.html"><img src="images/1logo.png" width="180px"></a>
        </div>
        <nav>
            <ul id="danh-sach">
                <li><a href="index.html">Trang chủ</a></li>
                <li><a href="products.html">Sản phẩm</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="">Contact</a></li>
                <li><a href="account.html">Tài khoản</a></li>
            </ul>
        </nav>
        <a href="cart.html"><img src="images/cart.png" width="30px" height="30px"></a>
        <img src="images/menu.png" class="danh-sach-icon" onclick="menutoggle()">
    </div>
</div>
<!-- ----------End menu--------- -->


<!-- ------cart items details--- -->
<div class="noi-dung-nho cart-page">
    <h2 class="tieu-de-cart">Giỏ hàng của bạn</h2>
    <table>
        <tr>
            <th>Tên sản phẩm</th>
            <th>Kích cỡ</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Chọn</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.cart.items}">
            <tr>
                <td>
                    <div class="thong-tin">
                        <img src="imgs/${item.product.images[0]}">
                        <div>
                            <p>${item.product.name}</p>
                            <small>${item.product.price}</small>
                            <br>
                            <a href="/item?id=${item.id}&num=0">Xóa</a>
                        </div>
                    </div>
                </td>
                <td>${item.size}</td>
                <td>
                    <button><a href="/item?id=${item.id}&num=-1">-</a></button>
                        ${item.quantity}
                    <button><a href="/item?id=${item.id}&num=1">+</a></button>
                </td>
                <td class="money-${item.id}">${item.money}</td>
                <td><input type="checkbox" value="${item.id}" name="select-item"></td>
            </tr>
        </c:forEach>

    </table>


    <div class="tong-gia">
        <table>
            <tr>
                <td>Tổng</td>
                <td><input class="total" value="0" name="total"></td>
            </tr>
        </table>
    </div>

    <div class="dat-hang">
        <input type="submit" value="Đặt hàng">
    </div>
</div>
<!-- ----End cart items details- -->


<!-- -----------Footer---------- -->
<div class="cuoi-trang">
    <div class="noi-dung">
        <div class="hang">
            <div class="cuoi-trang-cot-1">
                <h3>Tải xuống ứng dụng</h3>
                <p>Tải xuống ứng dụng cho Andoird và iOS</p>
                <div class="app-logo">
                    <img src="images/play-store.png">
                    <img src="images/app-store.png">
                </div>
            </div>
            <div class="cuoi-trang-cot-2">
                <img src="images/1logo.png">
                <p>Mục đích của chúng tôi là tạo niềm vui cho mọi người bằng những sản phẩm giá rẻ nhất thị
                    trường.</p>
            </div>
            <div class="cuoi-trang-cot-3">
                <h3>Liên kết hữu ích</h3>
                <ul>
                    <li>Coupons</li>
                    <li>Blog</li>
                    <li>Chính sách hoàn trả</li>
                    <li>Hội viên</li>
                </ul>
            </div>
            <div class="cuoi-trang-cot-4">
                <h3>Follow Us</h3>
                <ul>
                    <li>Facebook</li>
                    <li>Twitter</li>
                    <li>Istagram</li>
                    <li>Youtube</li>
                </ul>
            </div>
        </div>
        <hr>
    </div>
</div>
<!-- ---------End Footer-------- -->


<!-- ------js for toggle menu -- -->
<script>        //Hiển thị menu với màn hình khác nhau
var danhsach = document.getElementById("danh-sach")

danhsach.style.maxHeight = "0px";

function menutoggle() {
    if (danhsach.style.maxHeight == "0px") {
        danhsach.style.maxHeight = "200px";
    } else {
        danhsach.style.maxHeight = "0px";
    }
}
</script>
<!-- ----End js for toggle menu- -->
<script>
    var selectItem = document.querySelectorAll("input[type = 'checkbox']")
    var totalMoney = document.querySelector(".total")
    selectItem.forEach(function (select) {
        select.onclick = function () {
            var money = document.querySelector(".money-" + this.value);
            if (this.checked == true) {
                totalMoney.value = eval(totalMoney.value + "+" + money.innerHTML)
            } else {
                totalMoney.value = eval(totalMoney.value + "-" + money.innerHTML)
            }
        }
    })
</script>
</body>

</html>
