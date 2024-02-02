package com.simplegardening.cli.view;

import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.cli.utils.CLIReader;
import com.simplegardening.exception.BeanException;

import java.io.IOException;
import java.util.List;

public class LoginViewCLI {
    public LoginBeanIn getLoginInformation() throws IOException, BeanException {
        System.out.println("Login");
        System.out.print("Insert username: ");
        String login = CLIReader.readline();
        System.out.print("Insert password: ");
        String password = CLIReader.readline();
        String p = CLIReader.multiChoiceString(List.of("JDBC", "FILE_SYSTEM"));
        return new LoginBeanIn(login, password, p);

    }

}
