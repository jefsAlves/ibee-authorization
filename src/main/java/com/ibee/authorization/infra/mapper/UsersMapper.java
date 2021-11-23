package com.ibee.authorization.infra.mapper;

import com.ibee.authorization.infra.dto.UsersDTO;
import com.ibee.authorization.model.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Users toEntity(UsersDTO usersDTO) {
        return modelMapper.map(usersDTO, Users.class);
    }

    public UsersDTO toDTO(Users users) {
        return modelMapper.map(users, UsersDTO.class);
    }

}
