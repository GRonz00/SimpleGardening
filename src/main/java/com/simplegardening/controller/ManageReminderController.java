package com.simplegardening.controller;

import com.simplegardening.bean.in.ReminderInBean;
import com.simplegardening.dao.ReminderDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.SessionManager;

import java.sql.SQLException;

public class ManageReminderController {


    public void setReminder(ReminderInBean bean) throws ControllerException {
        try {
            new ReminderDAO().saveReminder(bean.getPlant(),bean.getType(), bean.getTime(), SessionManager.getInstance().getSession(bean.getIdSession()));
        } catch (SQLException e) {
            throw new ControllerException("SQL",e);
        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
