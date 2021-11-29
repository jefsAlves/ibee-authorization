package com.ibee.authorization.api.dto.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

    EMAIL_ALREADY_EXIST("email", "resource already exist");

    private String uri;
    private String title;

    private ErrorType(String uri, String path) {
        this.uri = "http://localhost:8080/" + uri;
        title = path;
    }

}

