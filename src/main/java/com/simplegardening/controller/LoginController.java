package com.simplegardening.controller;


import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.bean.out.LoginBeanOut;
import com.simplegardening.dao.UserDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.DatabaseException;
import com.simplegardening.model.Client;
import com.simplegardening.model.Session;
import com.simplegardening.model.SessionManager;
import com.simplegardening.model.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public LoginBeanOut login(LoginBeanIn bean) throws ControllerException {
        User user;
        LoginBeanOut loginBeanOut = new LoginBeanOut();
        try {
            user = UserDAO.getUserByUsername(bean.getLoginUsername());
            if (!Objects.equals(user.getPassword(), bean.getLoginPassword())) {
                throw new ControllerException("Invalid input: password");
            }
            if (user instanceof Client) {
                Session session = SessionManager.getInstance().createNewSession(user);
                loginBeanOut.setIdSession(session);
                loginBeanOut.setTypeUser(session);
                // ((Client)user).setStore(StoreDAO.getStoreByStoreOwnerUsername(user.getUsername()));
            }
        }
        catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        return loginBeanOut;

    }
}
