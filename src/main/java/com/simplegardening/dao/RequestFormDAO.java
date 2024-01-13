package com.simplegardening.dao;

import com.simplegardening.model.Pro;
import com.simplegardening.model.RequestForm;
import com.simplegardening.model.Session;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestFormDAO {
    public void saveRequestForm(Pro pro, RequestForm req,Session session) throws SQLException {

        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        // Create Connection
        try  {
            String sql = String.format("INSERT INTO RequestForm (pro,start,end,basePrice,extraPrice,pickUpAvi,pickUpBasePrice,pickUpKMPrice,newDiscount,availability,kmMax,typePlant,sizePlant) VALUES (?,?,?,?,%d,%b,?,?,%b,%d,%d,?,?)",req.getExtraHoliday(),req.isPickupAvailable(),req.isNewCustomer(),req.getAmount(),req.getMaxKm());
            // Execute query
            statement = connection.prepareStatement(sql);
            statement.setString(1,pro.getUsername());
            statement.setDate(2,req.getStart());
            statement.setDate(3,req.getEnd());
            statement.setFloat(4,req.getBasePrice());
            statement.setFloat(5,req.getPickupBasePrice());
            statement.setFloat(6,req.getKmPrice());
            statement.setString(7,req.getPlantType().toString());
            statement.setString(8,req.getPlantSize().toString());
            statement.executeUpdate();
            statement.close();


        }catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
        }
    }

    public List<RequestForm> getAllRequestForm(Session session) throws SQLException {
        List<RequestForm> requestFormList = new ArrayList<>();
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM '%s' ","RequestForm");
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                requestFormList.add(setRequestForm(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return requestFormList;
    }

    private RequestForm setRequestForm(ResultSet rs) throws SQLException {
        Date start= rs.getDate("start");
        Date end= rs.getDate("end");
        float basePrice = rs.getFloat("basePrice");
        boolean pickupAvailable= rs.getBoolean("pickUpAvi");
        int maxKm= rs.getInt("kmMax");
        float pickupBasePrice = rs.getFloat("pickUpBasePrice");
        float kmPrice = rs.getFloat("pickUpKMPrice");
        PlantSize plantSize = PlantSize.valueOf(rs.getString("sizePlant"));
        PlantType plantType = PlantType.valueOf(rs.getString("typePlant"));
        boolean newCustomer = rs.getBoolean("newDiscount");
        int extraHoliday = rs.getInt("extraPrice");
        int amount = rs.getInt("availability");
        String pro = rs.getString("pro");
        int id = rs.getInt("idrequestForm");
        RequestForm requestForm = new RequestForm(start,end,basePrice,pickupAvailable,maxKm,pickupBasePrice, kmPrice);
        requestForm.setIdRequestForm(id);
        requestForm.requestForm2(plantSize,plantType,newCustomer,extraHoliday,amount);
        requestForm.setPro(pro);
        return requestForm;
    }

    public RequestForm getRequestFormFromId(int idRequestForm, Session session) throws SQLException {
        RequestForm requestForm;
        Connection connection = session.getConnection();
        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM RequestForm WHERE idrequestForm = %d ",idRequestForm);
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            requestForm=setRequestForm(rs);
            rs.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return requestForm;
    }

    public void decreaseAviability(int idRequestForm,Session session) throws SQLException {
        PreparedStatement statement= null;
        Connection connection = session.getConnection();
        try  {
            String sql = String.format("UPDATE RequestForm set availability = availability-1 WHERE idrequestForm = '%d' ",idRequestForm);
            statement= connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }finally {
            assert statement != null;
            statement.close();
        }
    }
}
