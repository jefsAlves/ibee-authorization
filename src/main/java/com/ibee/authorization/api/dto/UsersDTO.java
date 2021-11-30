package com.ibee.authorization.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
