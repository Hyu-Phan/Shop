package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import model.Product;
import model.ProductDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditProductServlet", value = "/edit-product")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.getRequestDispatcher("edit_product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int categoryId = 1;

            String[] idDetails = request.getParameterValues("detail");
            String[] sizeList = request.getParameterValues("size");

            String[] colors = request.getParameterValues("color");

            String[] quantities = request.getParameterValues("quantity");
            List<ProductDetail> productDetails = new ArrayList<>();
            int i = 0;
            if (sizeList != null) {
                for (i = 0; i < idDetails.length; i++) {
                    productDetails.add(new ProductDetail(Integer.parseInt(idDetails[i]), sizeList[i], colors[i], Integer.parseInt(quantities[i])));
                }
                for (; i < sizeList.length; i++) {
                    productDetails.add(new ProductDetail(sizeList[i], colors[i], Integer.parseInt(quantities[i])));
                }
            }

            List<String> images = new ArrayList<>();
//            for (Part part : request.getParts()) {
//                if ("file".equals(part.getName())) {
//                    String realPath = request.getServletContext().getRealPath("/images");
//                    String fileName = extractFileName(part);
//                    if (!Files.exists(Paths.get(realPath))) {
//                        Files.createDirectory(Paths.get(realPath));
//                    }
//                    String path = realPath + "/" + fileName;
//                    System.out.println(path);
//                    part.write(path);
//                    images.add(fileName);
//                }
//            }

            Product product = new Product(id, name, price, new CategoryDAO().getCategoryById(categoryId), productDetails, description, images);
            ProductDAO pd = new ProductDAO();
            pd.update(product);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/product").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
