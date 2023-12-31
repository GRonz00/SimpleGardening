package com.simplegardening.exception;

import java.io.Serial;

public class APIException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public APIException(String reason){
        super(reason);
    }
}
