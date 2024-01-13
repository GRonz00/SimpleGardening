package com.simplegardening.controller;

import com.simplegardening.bean.in.AddPlantInBean;
import com.simplegardening.bean.out.ListPlantOutBean;
import com.simplegardening.dao.PlantDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.Plant;
import com.simplegardening.model.Session;
import com.simplegardening.model.SessionManager;

import java.sql.SQLException;
import java.util.List;

public class AddPlantController {
    public void savePlant(AddPlantInBean addPlantInBean) throws ControllerException {
        try {
            SessionManager.getInstance().validSession(addPlantInBean.getIdSession());
            Session session = SessionManager.getInstance().getSession(addPlantInBean.getIdSession());
            Plant plant = new Plant(session.getUser(), addPlantInBean.getName(), addPlantInBean.getSize(),addPlantInBean.getType(),addPlantInBean.getImage());
            PlantDAO.savePlant(plant,session);
        }catch (SQLException e){
            throw new ControllerException("There is a plant whit the same name");
        } catch (SessionException e) {
            throw new ControllerException(e);
        }

    }

    public ListPlantOutBean getPlants(int idSession) throws ControllerException {
        try {
            SessionManager.getInstance().validSession(idSession);
            Session session = SessionManager.getInstance().getSession(idSession);
            PlantDAO plantDAO = new PlantDAO();
            List<Plant> plants = plantDAO.getPlantsFromClient(session.getUser(),session);
            ListPlantOutBean listPlantOutBean = new ListPlantOutBean();
            listPlantOutBean.setPlant(plants);
            return listPlantOutBean;
        } catch (SQLException e) {
            throw new ControllerException("SQL",e.getCause());
        } catch (SessionException e) {
            throw new ControllerException(e);
        }

    }
}
