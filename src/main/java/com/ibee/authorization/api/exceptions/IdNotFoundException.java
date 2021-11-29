package com.ibee.authorization.api.exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
    }

    public IdNotFoundException(String msg) {
        super(msg);
    }
}
