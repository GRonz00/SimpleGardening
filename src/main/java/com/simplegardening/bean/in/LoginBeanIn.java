package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

public class LoginBeanIn {
    private String loginUsername;
    private String loginPassword;

    public LoginBeanIn(String username, String password) throws BeanException {
        setUsername(username);
        setPassword(password);
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setUsername(String loginUser) throws BeanException {
        if (loginUser.length() < 4) throw new BeanException("login username", BeanException.TOO_SHORT_REASON);
        if (loginUser.length() > 16) throw new BeanException("login username", BeanException.TOO_LONG_REASON);
        this.loginUsername = loginUser;
    }

    public void setPassword(String loginPass) throws BeanException {
        if (loginPass.length() < 4) throw new BeanException("login password", BeanException.TOO_SHORT_REASON);
        if (loginPass.length() > 16) throw new BeanException("login password", BeanException.TOO_LONG_REASON);
        this.loginPassword = loginPass;
    }
}
