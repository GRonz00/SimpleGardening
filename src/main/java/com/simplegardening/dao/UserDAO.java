package com.simplegardening.dao;

import com.simplegardening.exception.DatabaseException;
import com.simplegardening.model.Client;
import com.simplegardening.model.Pro;
import com.simplegardening.model.User;
import com.simplegardening.utils.UserType;

import java.sql.*;

public class UserDAO {
    public  User getUserByUsername(String loginUsername) throws DatabaseException, SQLException {
        // Create Connection
        User user;

        try (Connection connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("USER"), System.getenv("DATABASE_P"))) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Get user with specified username
                String sql = String.format("SELECT * FROM User WHERE Username='%s'", loginUsername);
                // Execute query
                ResultSet rs = statement.executeQuery(sql);
                // Empty result
                if (!rs.first())
                    throw new DatabaseException("username");

                rs.first();
                String password = rs.getString("password");
                String longitude = rs.getString("lon");
                String latitude = rs.getString("lat");
                UserType type = UserType.valueOf(rs.getString("type").toUpperCase());
                String address = rs.getString("address");
                double lon = Double.parseDouble(longitude.replace(',','.'));
                double lat = Double.parseDouble(latitude.replace(',','.'));

                if(type.equals(UserType.PRO)){
                user = new Pro(loginUsername,password,lon,lat,address);}
                else {
                    user= new Client(loginUsername,password,lon,lat,address);
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return user;
    }

    public void registerUser(String username, String password, String address, UserType userType, double longitude, double latitude) throws SQLException {
        String lon = String.valueOf(longitude);
        String lat = String.valueOf(latitude);
        // Create Connection
        try (Connection connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("USER"), System.getenv("DATABASE_P"))) {
            // Create statement
            try (Statement statement =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format("INSERT INTO User (username, password, type, lon, lat, address) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", username, password, userType.toString().toLowerCase(),  lon, lat,address);
                // Execute query
              statement.executeUpdate(sql);
            }
        }
    }
}
