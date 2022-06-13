/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

//import dao.ProductDAO;

import dal.CategoryDAO;
import dal.ProductDAO;
import model.Product;
import model.ProductDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pqhuy
 */

// Xử lý request có file
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] sizeList = request.getParameterValues("size");
        for (String size : sizeList) {
            System.out.println(size);
        }
        String[] colors = request.getParameterValues("color");
        for (String color : colors) {
            System.out.println(color);
        }
        String[] quantities = request.getParameterValues("quantity");
        for (String quantity : quantities) {
            System.out.println(quantity);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String[] sizeList = request.getParameterValues("size");

            String[] colors = request.getParameterValues("color");

            String[] quantities = request.getParameterValues("quantity");
            List<ProductDetail> productDetails = new ArrayList<>();
            if (sizeList != null) {
                for (int i = 0; i < sizeList.length; i++) {
                    productDetails.add(new ProductDetail(sizeList[i], colors[i], Integer.parseInt(quantities[i])));
                }
            }

            List<String> images = new ArrayList<>();
            for (Part part : request.getParts()) {
                if ("file".equals(part.getName())) {
                    String realPath = request.getServletContext().getRealPath("/images");
                    String fileName = extractFileName(part);
                    if (!Files.exists(Paths.get(realPath))) {
                        Files.createDirectory(Paths.get(realPath));
                    }
                    String path = realPath + "/" + fileName;
                    System.out.println(path);
                    part.write(path);
                    images.add(fileName);
                }
            }

//            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//           
//            if (!isMultipart) {
//                System.out.println("loi");
//            } else {
// 
//                FileItemFactory factory = new DiskFileItemFactory();
//                ServletFileUpload upload = new ServletFileUpload(factory);
//                List items = null;
//                try {
//                    items = upload.parseRequest(request);
//                } catch (FileUploadException e) {
//                    e.printStackTrace();
//                }
//                Iterator<FileItem> itr = items.iterator();
//                HashMap<String, String> fields = new HashMap<>();
//                while (itr.hasNext()) {
//                    FileItem item = itr.next();
//                    if (!item.isFormField()) {
//                        System.out.println("success");
//                        try {
//                            String filename = item.getName();
//                            System.out.println(filename);
//                            Path path = Paths.get(filename);
//                            String realPath = getServletContext().getRealPath("/uploads");
//                            System.out.println(realPath);
//                            if (!Files.exists(Paths.get(realPath))) {
//                                Files.createDirectory(Paths.get(realPath));
//                            }
//                            
//                            File savedFile = new File(realPath + "/" + filename);
//
//                            item.write(savedFile);
//                        } catch (Exception e) {
//                            System.out.println("loi upload");
//                        }
//                    }
//                }
//            }

            Product product = new Product(id, name, price, new CategoryDAO().getCategoryById(categoryId), productDetails, description, images);
            ProductDAO pd = new ProductDAO();
            String msg = pd.insert(product);

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("product.jsp").forward(request, response);

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

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
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
