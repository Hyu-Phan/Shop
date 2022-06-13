/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pqhuy
 */
public class ItemDAO extends ConnectionDB {
    public void insertItem(int idCart, Item item, String productId, String size) {
        try {
            String sql = "select * from item where cart_id = ? and product_id = ? and size = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idCart);
            st.setString(2, productId);
            st.setString(3, size);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                sql = "update shop.item set quantity = quantity + ?, money = money + ? where cart_id = ? and product_id = ?";
                st = connection.prepareStatement(sql);
                st.setInt(1, item.getQuantity());
                st.setDouble(2, item.getMoney());
                st.setInt(3, idCart);
                st.setString(4, productId);
                st.executeUpdate();
            } else {
                sql = "insert into item(quantity, money, product_id, cart_id, size) values(?, ?, ?, ?, ?)";
                st = connection.prepareStatement(sql);
                st.setInt(1, item.getQuantity());
                st.setDouble(2, item.getMoney());
                st.setString(3, productId);
                st.setInt(4, idCart);
                st.setString(5, item.getSize());
                st.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

    public Item getItemById(int itemId) {
        try {
            String sql = "select * from item where id = ? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, itemId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Item(rs.getInt("id"),
                        new ProductDAO().getProductById(rs.getString("product_id")),
                        rs.getString("size"),
                        rs.getInt("quantity"),
                        rs.getDouble("money"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateItem(Item item) {
        try {
            String sql = "update shop.item set quantity = ?, money = ? where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item.getQuantity());
            st.setDouble(2, item.getMoney());
            st.setInt(3, item.getId());
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemById(int itemId) {
        try {
            String sql = "delete from item where id = ? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, itemId);
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemsByCart(int idCart) {
        try {
            String sql = "select * from item where cart_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idCart);
            ResultSet rs = st.executeQuery();
            List<Item> items = new ArrayList<>();
            ProductDAO productDAO = new ProductDAO();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setMoney(rs.getDouble("money"));
                item.setProduct(productDAO.getProductById(rs.getString("product_id")));
                item.setSize(rs.getString("size"));
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
