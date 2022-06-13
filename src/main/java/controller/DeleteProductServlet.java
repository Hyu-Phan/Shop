package controller;

import dal.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            ProductDAO pd = new ProductDAO();
            pd.delete(id);
            String msg = "Xóa sản phẩm thành công";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
