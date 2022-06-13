package controller;

import dal.CartDAO;
import dal.ItemDAO;
import model.Cart;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "cart", value = "/cart")
public class GetCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                CartDAO cartDAO = new CartDAO();
                cart = cartDAO.getCartById(1);
                if (cart == null) {
                    cartDAO.insertCart(1);
                    cart = cartDAO.getCartById(1);
                }
            }
            ItemDAO itemDAO = new ItemDAO();
            List<Item> items = itemDAO.getItemsByCart(cart.getId());
            cart.setItems(items);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("cart1.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
