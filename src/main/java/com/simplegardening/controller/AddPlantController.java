package com.simplegardening.controller;

import com.simplegardening.bean.in.PlantInBean;
import com.simplegardening.bean.in.SessionBeanIn;
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
    public void savePlant(PlantInBean plantInBean) throws ControllerException {
        try {
            SessionManager.getInstance().validSession(plantInBean.getIdSession());
            Session session = SessionManager.getInstance().getSession(plantInBean.getIdSession());
            Plant plant = new Plant(session.getUser(), plantInBean.getName(), plantInBean.getSize(), plantInBean.getType(), plantInBean.getImage());
            PlantDAO.savePlant(plant,session);
        }catch (SQLException e){
            throw new ControllerException("There is a plant whit the same name");
        } catch (SessionException e) {
            throw new ControllerException(e);
        }

    }

    public ListPlantOutBean getPlants(SessionBeanIn idSession) throws ControllerException {
        try {
            SessionManager.getInstance().validSession(idSession.getIdSession());
            Session session = SessionManager.getInstance().getSession(idSession.getIdSession());
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
