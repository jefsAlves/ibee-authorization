package com.ibee.authorization.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordDTO {

	private String passwordCurrent;
	private String passwordNew;
}