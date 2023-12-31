package com.simplegardening.exception;

import java.io.Serial;

public class BeanException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TOO_SHORT_REASON = "too short";
    public static final String TOO_LONG_REASON = "too long";

    public static final String ONLY_REG = "aren't just regular characters";

    public static final String ONLY_NUMBER_REASON = "can just be numbers";


    public BeanException(String data, String reason) {
        super("Invalid data: " + data + ". Reason: " + reason);
    }

}
