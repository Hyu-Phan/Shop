package controller;

import dal.CartDAO;
import dal.ItemDAO;
import dal.ProductDAO;
import model.Cart;
import model.Item;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "item", value = "/item")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("id"));
        int num = Integer.parseInt(request.getParameter("num"));
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getItemById(itemId);
        if (num == 0) {
            itemDAO.deleteItemById(itemId);
        } else {
            int newNum = item.getQuantity() + num;
            if (newNum == 0) {
                itemDAO.deleteItemById(itemId);
            } else {
                item.setQuantity(newNum);
                item.setMoney(item.getProduct().getPrice() * newNum);
                itemDAO.updateItem(item);
            }
        }
        response.sendRedirect("/cart");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String productId = request.getParameter("productId");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("num"));
        CartDAO cartDAO = new CartDAO();
        ItemDAO itemDAO = new ItemDAO();
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
        Item item = new Item();
        item.setQuantity(quantity);
        item.setMoney(quantity * product.getPrice());
        item.setSize(size);
        Cart cart = cartDAO.getCartById(userId);
        String msg = "";
        if (cart != null) {
            itemDAO.insertItem(cart.getId(), item, productId, size);
            msg = "Thêm sản phẩm vào giỏ hàng thành công";
        } else {
            msg = "Không thấy giỏ hàng";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("id", productId);
        request.getRequestDispatcher("show.jsp").forward(request, response);
    }
}
