package com.simplegardening.controller;


import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.bean.out.LoginBeanOut;
import com.simplegardening.dao.UserDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.DatabaseException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.*;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public LoginBeanOut login(LoginBeanIn bean) throws ControllerException {
        User user;
        LoginBeanOut loginBeanOut = new LoginBeanOut();
        UserDAO userDAO = new UserDAO();
        try {
            user = userDAO.getUserByUsername(bean.getLoginUsername());
            if (!Objects.equals(user.getPassword(), bean.getLoginPassword())) {
                throw new ControllerException("Invalid input: password");
            }
            if (user instanceof Client) {
                Session session = SessionManager.getInstance().createNewSession(user,bean.getType());
                loginBeanOut.setIdSession(session);
                loginBeanOut.setTypeUser(session);
            }
            if (user instanceof Pro) {
                Session session = SessionManager.getInstance().createNewSession(user,bean.getType());
                loginBeanOut.setIdSession(session);
                loginBeanOut.setTypeUser(session);
            }
        }
        catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        return loginBeanOut;

    }

    public void closeSession(int idSession) throws ControllerException {
        try {
            SessionManager.getInstance().closeSession(idSession);
        } catch (SQLException | SessionException e) {
            throw new ControllerException(e);
        }
    }
}
