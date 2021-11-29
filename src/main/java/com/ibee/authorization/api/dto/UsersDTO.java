package com.ibee.authorization.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@NoArgsConstructor
@Getter
@Setter
public class UsersDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty("password")
    private String password;

    public UsersDTO(String email) {
        this.user = email;
    }

}
