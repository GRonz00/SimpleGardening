package com.simplegardening.exception;

import java.io.Serial;

public class ControllerException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String DATABASE = "Database";

    public ControllerException(String reason) {
        super(reason);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String reason, Throwable cause) {
        super(reason + " - " + cause.getMessage(), cause);
    }
}
