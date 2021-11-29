package com.ibee.authorization.util;

import com.ibee.authorization.api.exceptions.IdNotFoundException;
import com.ibee.authorization.api.exceptions.RestaurantException;
import com.ibee.authorization.infra.repository.UsersRepository;
import com.ibee.authorization.model.entities.Users;
import com.ibee.authorization.model.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersUtil {

    @Autowired
    private UsersRepository usersRepository;

    public Users verifyUserExist(Long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        return users.orElseThrow(() -> new IdNotFoundException(MessageUtil.ID_NOT_FOUND));
    }

    public Users validateUserBeforeCreate(String userName) {
        var users = usersRepository.findByUser(userName);
        if(users != null) {
            throw new BusinessException(MessageUtil.EMAIL_ALREADY_EXIST);
        }
        return users;
    }

}
