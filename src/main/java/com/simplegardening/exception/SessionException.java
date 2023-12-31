package com.simplegardening.exception;

import java.io.Serial;

public class SessionException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public SessionException(String reason) {
        super(reason);
    }


}
