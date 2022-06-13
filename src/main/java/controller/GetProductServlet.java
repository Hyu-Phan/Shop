/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author pqhuy
 */
public class GetProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            ProductDAO pd = new ProductDAO();
            Product product = pd.getProductById(id);
            if (product != null) {
                List<Product> relatedProducts = pd.getRelatedProducts(product.getId(), product.getCategory().getId());
                request.setAttribute("product", product);
                request.setAttribute("relatedProducts", relatedProducts);
            } else {
                String msg = "Không tìm thấy sản phẩm phù hợp với id";
                request.setAttribute("msg", msg);
            }
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
