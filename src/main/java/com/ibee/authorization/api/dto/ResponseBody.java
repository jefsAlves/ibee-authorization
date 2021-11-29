package com.ibee.authorization.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private LocalDateTime timestamp;


}