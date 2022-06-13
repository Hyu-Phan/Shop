package dal;

import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends ConnectionDB {
    public List<Category> getCategories() {
        try {
            String sql = "select * from category c";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category getCategoryById(int id) {
        try {
            String sql = "select * from category c where c.id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            List<Category> categories = new ArrayList<>();
            if (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category getCategoryByProductId(String productId) {
        try {
            String sql = "select * from category c, product p where c.id = p.category_id and p.id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            List<Category> categories = new ArrayList<>();
            if (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
