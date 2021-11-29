package com.ibee.authorization.api.exceptions;

public class RestaurantException extends RuntimeException {

    public RestaurantException() {
    }

    public RestaurantException(String msg) {
        super(msg);
    }
}
