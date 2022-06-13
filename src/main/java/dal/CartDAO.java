/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author pqhuy
 */
public class CartDAO extends ConnectionDB {

    public void insertCart(int userId) {
        try {
            String sql = "insert into cart(user_id) values(?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cart getCartById(int userId) {
        try {
            String sql = "select * from cart where user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                return cart;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
