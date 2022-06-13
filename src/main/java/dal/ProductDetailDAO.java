/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.ProductDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pqhuy
 */
public class ProductDetailDAO extends ConnectionDB {
    public void insert(String productId, ProductDetail productDetail) {
        try {
            String sql = "insert into product_detail(product_id, size, color, quantity) values(?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.setString(2, productDetail.getSize());
            st.setString(3, productDetail.getColor());
            st.setInt(4, productDetail.getQuantity());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDetail> findByProductID(String productId) {
        try {
            String sql = "select * from product_detail where product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            List<ProductDetail> productDetails = new ArrayList<>();
            while (rs.next()) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setId(rs.getInt("id"));
                productDetail.setSize(rs.getString("size"));
                productDetail.setColor(rs.getString("color"));
                productDetail.setQuantity(rs.getInt("quantity"));
                productDetails.add(productDetail);
            }
            return productDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductDetail findById(int id) {
        try {
            String sql = "select * from product_detail where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setId(rs.getInt("id"));
                productDetail.setSize(rs.getString("size"));
                productDetail.setColor(rs.getString("color"));
                productDetail.setQuantity(rs.getInt("quantity"));
                return productDetail;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateQuantity(String productId, String size, int num) {
        try {
            String sql = "update product_detail set quantity = quantity + ? where product_id = ? and size = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, num);
            st.setString(2, productId);
            st.setString(3, size);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductDetail productDetail) {
        try {
            String sql = "update product_detail set size = ?, color = ?, quantity = ? where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productDetail.getSize());
            st.setString(2, productDetail.getColor());
            st.setInt(3, productDetail.getQuantity());
            st.setInt(4, productDetail.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "delete from product_detail where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(String productId) {
        try {
            String sql = "delete from product_detail where product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
