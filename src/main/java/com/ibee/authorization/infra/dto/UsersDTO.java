package com.ibee.authorization.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UsersDTO {

    private String email;
    private String password;

    public UsersDTO(String email) {
        this.email = email;
    }

}
