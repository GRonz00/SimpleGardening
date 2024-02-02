package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.ReminderInBean;
import com.simplegardening.cli.view.ReminderViewCLI;
import com.simplegardening.controller.ManageReminderController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;
import java.util.List;

public class ReminderGraphicControllerCLI {
    public void initialize(int idSession){
        try {
            ReminderViewCLI reminderViewCLI = new ReminderViewCLI();
            List<String> reminder = reminderViewCLI.getReminder();
            ReminderInBean reminderInBean = new ReminderInBean(idSession,reminder.get(0),reminder.get(1),reminder.get(2),reminder.get(3));
            ManageReminderController manageReminderController = new ManageReminderController();
            manageReminderController.setReminder(reminderInBean);
        } catch (IOException | BeanException | ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(idSession);
        }

    }
}
