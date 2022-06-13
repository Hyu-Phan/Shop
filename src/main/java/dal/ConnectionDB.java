/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author pqhuy
 */
public class ConnectionDB {
    protected Connection connection;

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/shop";
            final String username = "root";
            final String password = "phuy2712@";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
