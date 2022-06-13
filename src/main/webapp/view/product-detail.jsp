<%--
  Created by IntelliJ IDEA.
  User: pqhuy
  Date: 5/20/2022
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LapStore - Tất cả sản phẩm</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<!-- deleted div header -->
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

<!-- -----------------Single product-details----------------- -->
<c:set var="product" value="${requestScope.product}"></c:set>
<div class="noi-dung-nho san-pham">
    <div class="hang">
        <div class="cot-2">
            <img src="images/${product.images[0]}" width="100%" id="ProductImg">

            <div class="small-img-hang">
                <c:forEach items="${product.images}" var="image">
                    <div class="small-img-cot">
                        <img src="images/${image}" width="100%" class="small-img">
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="cot-2">
            <p>Home / Gucci</p>
            <h1>${product.name}</h1>
            <h4>${product.price}</h4>
            <form action="/item" method="post">
                <div class="select">
                    <select class="select-size" name="size">
                        <option>Sellect Sile</option>
                        <c:forEach var="productDetail" items="${product.productDetails}">
                            <option name="size">${productDetail.size}</option>
                        </c:forEach>
                    </select>
                    <p class="quantity"></p>
                </div>

                <input name="userId" value="${1}" hidden>
                <input name="productId" value="${product.id}" hidden>
                <input type="number" class="num" value="1" min="1" name="num"/>
                <button type="submit">Thêm vào giỏ hàng</button>
            </form>
            <a href="/item?userId=1&productId=${product.id}&size=${size}&quantity=${quantity}" class="btn">
                Thêm vào giỏ hàng
            </a>

            <h3>Chi tiết sản phẩm <i class="fa fa-indent"></i></h3>
            <br>
            <p>${product.description}</p><br>
            <p>Thể loại: Áo holldi</p> <br>
            <p>Màu: Đen</p> <br>
            <p>Phù hợp với mọi lứa tuổi</p><br>

        </div>
    </div>
</div>
<!-- ---------title---------- -->
<div class="noi-dung-nho">
    <div class="hang hang-2">
        <h2>Sản phẩm liên quan</h2>
        <p class="xemthem">Xem thêm</p>
    </div>
</div>

<!-- ---------------products--------------- -->

<div class="noi-dung-nho">

    <div class="hang">   <!-- Products 13-16 Lenovo -->
        <c:forEach var="product" items="${requestScope.relatedProducts}">
            <div class="cot-4">
                <a href="product-details.html">
                    <img src="images/${product.images[0]}">
                    <h4>${product.name}</h4>
                    <div class="xep-hang">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star-half-o"></i>
                    </div>
                    <p>${product.price}</p>
                </a>
            </div>
        </c:forEach>


        <div class="page-btn">  <!---- Nút chuyển trang ---->
            <span>1</span>
            <span>2</span>
            <span>3</span>
            <span>4</span>
            <span>&#8594;</span>
        </div>

    </div>
</div>
<!-- -----------cuoi-trang---------- -->
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
                <p>Mục đích của chúng tôi là tạo niềm vui cho mọi người bằng những sản phẩm giá rẻ nhất thị trường.</p>
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
<!-- ---------End cuoi-trang-------- -->

<!-- -------------- js for toggle menu ------------------ -->
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

<!-- ---------------js for product gallery------------ -->
<script>  //click ảnh nhỏ sẽ hiện ra ảnh lớn sp
var ProductImg = document.getElementById("ProductImg");
var SmallImg = document.getElementsByClassName("small-img");

SmallImg[0].onclick = function () {
    ProductImg.src = SmallImg[0].src;
}
SmallImg[1].onclick = function () {
    ProductImg.src = SmallImg[1].src;
}
SmallImg[2].onclick = function () {
    ProductImg.src = SmallImg[2].src;
}
SmallImg[3].onclick = function () {
    ProductImg.src = SmallImg[3].src;
}
</script>

<script>
    var selectSize = document.querySelector(".select-size");
    var quantity = document.querySelector(".quantity");
    var sizeList = []
    <c:forEach var="p" items="${product.productDetails}">
    var detail = {
        "size": '${p.size}',
        "quantity": ${p.quantity}
    }
    sizeList.push(detail)
    </c:forEach>
    console.log(sizeList)
    selectSize.onchange = function () {
        for (let i = 0; i < sizeList.length; i++) {
            if (sizeList[i].size === selectSize.value) {
                quantity.innerHTML = sizeList[i].quantity + " sản phẩm có sẵn";
                return;
            }
        }
        quantity.innerHTML = "";
    }

</script>
</body>
</html>
