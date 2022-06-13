package dal;

import model.Product;
import model.ProductDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author pqhuy
// */
public class ProductDAO extends ConnectionDB {
    public List<Product> getProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                products.add(p);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insert(Product product) {
        String msg = "";
        try {
            // Thêm bảng product
            String sql = "insert into product values(?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getId());
            st.setString(2, product.getName());
            st.setDouble(3, product.getPrice());
            st.setString(4, product.getDescription());
            st.executeUpdate();

            // Thêm bảng product_detail
            ProductDetailDAO productDetailDAO = new ProductDetailDAO();
            for (ProductDetail productDetail : product.getProductDetails()) {
                productDetailDAO.insert(product.getId(), productDetail);
            }

            // Thêm bảng product_image
            ImageDAO imageDAO = new ImageDAO();
            for (String image : product.getImages()) {
                imageDAO.insertImage(image, product.getId());
            }

            msg = "Thêm sản phẩm thành công";
        } catch (Exception e) {
            System.out.println("Error insert");
            msg = "Lỗi không thêm sản phẩm";
        }
        return msg;

    }

    public Product getProductById(String id) {
        try {
            String sql = "select * from product p where p.id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));

                product.setProductDetails(new ProductDetailDAO().findByProductID(product.getId()));
                product.setCategory(new CategoryDAO().getCategoryByProductId(product.getId()));
                product.setImages(new ImageDAO().getImages(product.getId()));

                return product;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getRelatedProducts(String id, int categoryId) {
        try {
            String sql = "select * from product p, category c where p.category_id = c.id and p.id != ? and c.id = ? order by rand() limit 4";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setInt(2, categoryId);
            ResultSet rs = st.executeQuery();
            List<Product> relatedProducts = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setImages(new ImageDAO().getImages(product.getId()));

                relatedProducts.add(product);
            }
            return relatedProducts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Product product) {
        try {
            String sql = "update product set name = ?, price = ?, description = ? where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setString(3, product.getDescription());
            st.setString(4, product.getId());
            st.executeUpdate();

            ProductDetailDAO productDetailDAO = new ProductDetailDAO();
            List<ProductDetail> details = productDetailDAO.findByProductID(product.getId());

            for (ProductDetail detail : product.getProductDetails()) {
                boolean exist = false;
                for (ProductDetail detail1 : details) {
                    if (detail.getId() == detail1.getId()) {
                        exist = true;
                        productDetailDAO.updateProduct(detail1);
                        details.remove(detail1);
                        break;
                    }
                }
                if (exist == false) {
                    productDetailDAO.insert(product.getId(), detail);
                }
            }
            for (ProductDetail detail : details) {
                productDetailDAO.delete(detail.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String productId) {
        try {
            ProductDetailDAO productDetailDAO = new ProductDetailDAO();
            productDetailDAO.deleteAll(productId);

            ImageDAO imageDAO = new ImageDAO();
            imageDAO.deleteAll(productId);

            String sql = "delete product where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
