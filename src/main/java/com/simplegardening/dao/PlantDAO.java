package com.simplegardening.dao;

import com.simplegardening.exception.DatabaseException;
import com.simplegardening.model.Plant;
import com.simplegardening.model.User;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO {


    public static void savePlant(Plant plant) throws SQLException{
        // Create Connection
        try {
            Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            PreparedStatement statement;
            String sql = String.format("INSERT INTO Plant ( name, type, size, Image,client) VALUES ('%s', '%s', '%s', ?, '%s')", plant.getName(),plant.getType().toString().toLowerCase(),plant.getSize().toString().toLowerCase(),plant.getClient().getUsername());
                // Execute query
            statement = connection.prepareStatement(sql);
            statement.setBlob(1,plant.getImage());
            statement.executeUpdate();
            statement.close();


            }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
        }
    public List<Plant> getPlantsFromClient(String usernameClient) throws SQLException {
        List<Plant> plants = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = String.format("SELECT * FROM Plant WHERE client = '%s'", usernameClient);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                plants.add(getPlant(rs));
            }
            rs.close();
        } catch (SQLException | DatabaseException e) {
            throw new SQLException(e);
        }
        return plants;

    }

    private Plant getPlant(ResultSet rs) throws SQLException, DatabaseException {
        User client = (UserDAO.getUserByUsername(rs.getString("client")));
        String name = rs.getString("name");
        PlantType type = PlantType.valueOf(rs.getString("type").toUpperCase());
        PlantSize size = PlantSize.valueOf(rs.getString("size").toUpperCase());
        Blob imageBlob = rs.getBlob("Image");
        InputStream stream = null;
        if (imageBlob != null)
            stream = imageBlob.getBinaryStream();
        return new Plant(client,name,size,type,stream);

    }
}


