package com.simplegardening.dao;

import com.simplegardening.exception.DatabaseException;
import com.simplegardening.model.Client;
import com.simplegardening.model.Plant;
import com.simplegardening.model.Session;
import com.simplegardening.model.User;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO {


    public static void savePlant(Plant plant, Session session) throws SQLException{
        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        try {

            String sql = String.format("INSERT INTO Plant ( name, type, size, Image,client,state) VALUES ('%s', '%s', '%s', ?, '%s','home')", plant.getName(),plant.getType().toString().toLowerCase(),plant.getSize().toString().toLowerCase(),plant.getClient().getUsername());
                // Execute query
            statement = connection.prepareStatement(sql);
            statement.setBlob(1,plant.getImage());
            statement.executeUpdate();
            statement.close();


            }catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            if (statement != null) statement.close();
        }
        }
    public List<Plant> getPlantsFromClient(User client,Session session) throws SQLException {
        List<Plant> plants = new ArrayList<>();
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM Plant WHERE client = '%s'", client.getUsername());
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                plants.add(getPlant(rs,client));
            }
            rs.close();
        } catch (SQLException | DatabaseException e) {
            throw new SQLException(e.getMessage());
        }
        return plants;

    }

    private Plant getPlant(ResultSet rs,User client) throws SQLException, DatabaseException {
        String name = rs.getString("name");
        PlantType type = PlantType.valueOf(rs.getString("type").toUpperCase());
        PlantSize size = PlantSize.valueOf(rs.getString("size").toUpperCase());
        Blob imageBlob = rs.getBlob("Image");
        InputStream stream = null;
        if (imageBlob != null)
            stream = imageBlob.getBinaryStream();
        return new Plant(client,name,size,type,stream);

    }

    public Plant getPlantFromName(String plantName, Client client,Session session) throws SQLException {
        Plant plant;
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM Plant WHERE client = '%s' and name='%s'", client.getUsername(),plantName);
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            plant=getPlant(rs,client);
            rs.close();
        } catch (SQLException | DatabaseException e) {
            throw new SQLException(e.getMessage());
        }
        return plant;

    }

    public void changeState(Plant plant, Session session) throws SQLException {
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                    String sql = String.format("UPDATE Plant set state = 'pending' WHERE client = '%s' and name='%s'", plant.getClient().getUsername(), plant.getName());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }
}


