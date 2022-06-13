var btn = document.querySelector(".them")
var detail = document.querySelector(".detail")
var inputs = document.querySelectorAll("input")
var deleteBtn = document.querySelectorAll(".xoa")
inputs.forEach(function (input) {
    input.onchange = function () {
        input.setAttribute("value", input.value)
    }
})
btn.onclick = function () {
    detail.innerHTML = detail.innerHTML + `
        <div class='chi-tiet'>
            Size <input type="text" name="size"/>
            Màu <input type="text" name="color"/>
            Số lượng <input type="number" name="quantity">
            <div class='xoa'> Xóa </div>
        </div>
    `
    inputs = document.querySelectorAll("input")
    inputs.forEach(function (input) {
        input.onchange = function () {
            input.setAttribute("value", input.value)
        }
    })
    deleteBtn = document.querySelectorAll(".xoa")
    deleteBtn.forEach(function (btn) {
        btn.onclick = function () {
            this.parentElement.remove()
        }
    })
}
deleteBtn.forEach(function (btn) {
    btn.onclick = function () {
        this.parentElement.remove()
    }
})