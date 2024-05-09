package com.example.Connections;

import java.sql.*;

public class DB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/ava_lestial_company";
    static final String USER = "root";
    static final String PASS = "";
    
    public static void main(String[] args){
        System.out.println("DB is started.");
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public boolean authenticateUser(String usernameOrEmail, String password) {
        String query = "SELECT * FROM pengguna WHERE (username_user = ? OR email_user = ?) AND password_user = ?";
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, usernameOrEmail);
            preparedStatement.setString(2, usernameOrEmail);
            preparedStatement.setString(3, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }


}
