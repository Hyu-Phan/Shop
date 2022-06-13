package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO extends ConnectionDB {

    public List<String> getImages(String id) {
        try {
            List<String> images = new ArrayList<>();
            String sql = "select * from product_image pi where pi.product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                images.add(rs.getString("image"));
            }
            return images;
        } catch (Exception e) {

        }
        return null;
    }

    public void insertImage(String image, String productId) {
        try {
            String sql = "insert into product_image(product_id, image) values(?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.setString(2, image);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "delete from product_image where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(String productId) {
        try {
            String sql = "delete from product_image where product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
