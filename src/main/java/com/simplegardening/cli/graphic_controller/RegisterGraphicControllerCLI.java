package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.AddressInBean;
import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.bean.in.RegisterInBean;
import com.simplegardening.bean.out.AddressOutBean;
import com.simplegardening.bean.out.LoginBeanOut;
import com.simplegardening.cli.view.RegisterViewCLI;
import com.simplegardening.controller.ConvertAddressController;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.RegisterController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;
import java.util.List;

public class RegisterGraphicControllerCLI {
    private final RegisterViewCLI registerViewCLI;

    public RegisterGraphicControllerCLI() {
        this.registerViewCLI = new RegisterViewCLI();
    }

    public void register() {
        List<String> s;
        LoginBeanOut beanOut;
        while (true) {
            try {
                s = registerViewCLI.getAddress();
                AddressInBean ad = new AddressInBean(s.get(3), s.get(4), s.get(6), s.get(5));
                AddressOutBean cord = new ConvertAddressController().convert(ad);
                String address = s.get(6) + s.get(7) + s.get(4) + s.get(5) + s.get(3);
                RegisterInBean registerInBean = new RegisterInBean(s.get(0), s.get(1), address, s.get(2), cord.getLongitude(), cord.getLatitude());
                new RegisterController().register(registerInBean);
                LoginBeanIn bean = new LoginBeanIn(s.get(0), s.get(1), "JDBC");
                LoginController controller = new LoginController();
                beanOut = controller.login(bean);
                break;
            } catch (ControllerException | BeanException | IOException e) {
                System.out.println("[ERR] " + e.getMessage());
                System.out.println("Please retry.");
            }
        }
            if (s.get(2).equals("client")) {
                HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
                homeGraphicControllerCLI.initialize(beanOut.getIdSession());
            }
            if (s.get(2).equals("pro")) {
                HomeProGraphicControllerCLI homeProGraphicControllerCLI = new HomeProGraphicControllerCLI();
                homeProGraphicControllerCLI.initialize(beanOut.getIdSession());
            }

        }
    }


