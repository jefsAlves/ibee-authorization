package com.ibee.authorization.model.services;

import com.ibee.authorization.infra.dto.UsersDTO;

public interface UsersService {

    UsersDTO createUser(UsersDTO usersDTO);

}