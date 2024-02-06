package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.bean.out.LoginBeanOut;
import com.simplegardening.cli.view.LoginViewCLI;
import com.simplegardening.controller.LoginController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;

public class LoginGraphicControllerCLI {
    private final LoginViewCLI loginView;

    public LoginGraphicControllerCLI() {
        loginView = new LoginViewCLI();
    }

    public void login() throws IOException {
        LoginBeanOut beanOut;
        while (true) {
            try {
                LoginBeanIn bean = loginView.getLoginInformation();
                LoginController controller = new LoginController();
                beanOut = controller.login(bean);
                break;
            } catch (ControllerException | BeanException e) {
                System.out.println("[ERR] " + e.getMessage());
                System.out.println("Please retry.");
            }
        }
        if (beanOut.getTypeUser()==1) {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(beanOut.getIdSession());
        }
        if (beanOut.getTypeUser()==2) {
            RequestProGraphicControllerCLI requestProGraphicControllerCLI = new RequestProGraphicControllerCLI();
            requestProGraphicControllerCLI.initialize(beanOut.getIdSession());
        }


    }
}
