package com.ibee.authorization.api.exceptions;

public class CannotDeleteException extends RuntimeException {

    public CannotDeleteException() {
    }

    public CannotDeleteException(String msg) {
        super(msg);
    }
}
