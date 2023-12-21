package com.simplegardening.controller;

import com.simplegardening.bean.in.RegisterInBean;
import com.simplegardening.dao.UserDAO;

public class RegisterController {
    public void register(RegisterInBean registerInBean){
        UserDAO userDAO = new UserDAO();
        userDAO.registerUser(registerInBean.getUsername(),registerInBean.getPassword(),registerInBean.getAddress(),registerInBean.getUserType(), registerInBean.getLongitude(), registerInBean.getLatitude());
        System.out.println("far");
    }
}
