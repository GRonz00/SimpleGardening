package com.simplegardening.controller;

import com.simplegardening.bean.in.RegisterInBean;
import com.simplegardening.dao.UserDAO;
import com.simplegardening.exception.ControllerException;

import java.sql.SQLException;

public class RegisterController {
    public void register(RegisterInBean registerInBean) throws ControllerException {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.registerUser(registerInBean.getUsername(),registerInBean.getPassword(),registerInBean.getAddress(),registerInBean.getUserType(), registerInBean.getLongitude(), registerInBean.getLatitude());
        } catch (SQLException e) {
            throw new ControllerException("Sql",e);
        }
    }
}
