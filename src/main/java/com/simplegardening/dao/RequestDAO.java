package com.simplegardening.dao;

import com.simplegardening.exception.DatabaseException;
import com.simplegardening.model.*;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public boolean newClient(User pro, User client,Session session) throws SQLException {
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM Request WHERE Plant_client = '%s' and pro='%s' and state= 'accepted'", client.getUsername(),pro.getUsername());
            ResultSet rs = statement.executeQuery(query);
            if(rs.next())return true;
            rs.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    public List<Request> getRequestFromUser(User user,Session session) throws SQLException, DatabaseException {
            String username = user.getUsername();
        List<Request> requests = new ArrayList<>();
        Connection connection = session.getConnection();
            try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String query = String.format("SELECT * FROM Request WHERE Plant_client = '%s' or pro = '%s'", username,username);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    requests.add(getRequest(rs,session));
                }
                rs.close();
            } catch (SQLException e) {
                throw new SQLException(e.getMessage());
            } catch (DatabaseException e) {
                throw new DatabaseException(e.getMessage());
            }
        return requests;

        }

    private Request getRequest(ResultSet rs, Session session) throws SQLException, DatabaseException {
        Date start = rs.getDate("start");
        Date end = rs.getDate("end");
        boolean pickup = rs.getBoolean("pickup");
        float price = rs.getFloat("price");
        String clientName = rs.getString("Plant_client");
        String proName = rs.getString("pro");
        String plantName = rs.getString("Plant_name");
        String state = rs.getString("state");
        RequestForm requestForm = new RequestFormDAO().getRequestFormFromId(rs.getInt("RequestForm_idrequestForm"),session);
        UserDAO userDAO = new UserDAO();
        Client client = (Client) userDAO.getUserByUsername(clientName);
        Pro pro = (Pro) userDAO.getUserByUsername(proName);
        Plant plant = new PlantDAO().getPlantFromName(plantName,client,session);
        Request request = new Request(plant,price,pickup,pro,client, Instant.ofEpochMilli(start.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate(),Instant.ofEpochMilli(end.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        request.setRequestForm(requestForm);
        request.setState(state);
        return request;




    }
    public void saveRequest(Request request,Session session) throws SQLException {
        PreparedStatement statement= null;
        Connection connection = session.getConnection();
        try  {
                String sql = String.format("INSERT INTO `Request` (`price`, `start`, `end`, `pro`, `Plant_client`, `Plant_name`,`state`,`pickup`,`RequestForm_idrequestForm`) VALUES (?, ?, ?, '%s', '%s', '%s','sent',?,%d)", request.getPro().getUsername(),request.getClient().getUsername(),request.getPlant().getName(),request.getRequestForm().getIdRequestForm());
                statement =connection.prepareStatement(sql);
                statement.setFloat(1,request.getPrice());
                statement.setDate(2,Date.valueOf(request.getStart()));
                statement.setDate(3,Date.valueOf(request.getEnd()));
                statement.setBoolean(4,request.isPickup());

            // Execute query
                statement.executeUpdate();
                statement.close();
            }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }finally {
            assert statement != null;
            statement.close();
        }
        }

    public void acceptRequest(Request request, Session session) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        try  {
            String sql = String.format("UPDATE Request set state = 'accepted' WHERE Plant_client = '%s' and Plant_name='%s' and pro='%s' and start = ? and end = ?", request.getClient().getUsername(),request.getPlant().getName(),request.getPro().getUsername());
            statement= connection.prepareStatement(sql);
            statement.setDate(1,Date.valueOf(request.getStart()));
            statement.setDate(2,Date.valueOf(request.getEnd()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }finally {
            assert statement != null;
            statement.close();
        }
    }

    public void refuseRequest(Request request, Session session) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        try  {
            String sql = String.format("UPDATE Request set state = 'rejected' WHERE Plant_client = '%s' and Plant_name='%s' and pro='%s' and start = ? and end = ?", request.getClient().getUsername(),request.getPlant().getName(),request.getPro().getUsername());
            statement= connection.prepareStatement(sql);
            statement.setDate(1,Date.valueOf(request.getStart()));
            statement.setDate(2,Date.valueOf(request.getEnd()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }finally {
            assert statement != null;
            statement.close();
        }
    }

    public boolean checkRequestDaysPlant(Request request,Session session) throws SQLException {
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM Request WHERE Plant_client = '%s' and Plant_name = '%s' and state='accepted'", request.getClient().getUsername(),request.getPlant().getName());
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                LocalDate start = Instant.ofEpochMilli(rs.getDate("start").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate end = Instant.ofEpochMilli(rs.getDate("end").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                if((start.isAfter(request.getStart())&&start.isBefore(request.getEnd()))||(end.isAfter(request.getStart())&&end.isBefore(request.getEnd())))return false;
            }
            rs.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return true;
    }

    public void updateStateRequestPlantDay(Request request, Session session) throws SQLException {
        PreparedStatement statement= null;
        Connection connection = session.getConnection();
        try  {
            String sql = String.format("UPDATE Request set state = 'rejected' WHERE Plant_client = '%s' and Plant_name='%s' and pro='%s' and start > ? and end < ?", request.getClient().getUsername(),request.getPlant().getName(),request.getPro().getUsername());
            statement= connection.prepareStatement(sql);
            statement.setDate(1,Date.valueOf(request.getStart()));
            statement.setDate(2,Date.valueOf(request.getEnd()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }finally {
            assert statement != null;
            statement.close();
        }
    }
}




