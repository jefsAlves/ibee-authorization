package com.ibee.authorization.model.services.impl;

import com.ibee.authorization.infra.dto.UsersDTO;
import com.ibee.authorization.infra.mapper.UsersMapper;
import com.ibee.authorization.infra.repository.UsersRepository;
import com.ibee.authorization.model.exceptions.BusinessException;
import com.ibee.authorization.model.exceptions.ExceptionMessage;
import com.ibee.authorization.model.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper mapper;

    @Override
    @Transactional
    public UsersDTO createUser(UsersDTO usersDTO) {
        var user = mapper.toEntity(usersDTO);
        verifyUserExist(user.getEmail());
        usersRepository.save(user);
        return new UsersDTO(usersDTO.getEmail());
    }

    private void verifyUserExist(String email) {
       if(email != null) {
           throw new BusinessException(ExceptionMessage.USER_ALREADY_EXIT);
       }
    }

}