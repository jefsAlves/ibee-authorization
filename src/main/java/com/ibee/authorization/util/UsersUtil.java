package com.ibee.authorization.util;

import com.ibee.authorization.api.dto.UsersDTO;
import com.ibee.authorization.api.exceptions.IdNotFoundException;
import com.ibee.authorization.api.exceptions.RestaurantException;
import com.ibee.authorization.infra.repository.UsersRepository;
import com.ibee.authorization.model.entities.Users;
import com.ibee.authorization.model.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersUtil {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users verifyUserExist(Long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        return users.orElseThrow(() -> new IdNotFoundException(MessageUtil.ID_NOT_FOUND));
    }

    public void validateUser(Users users) {
        validateUserBeforeCreate(users);
        encrypt(users);
    }

    public Users validateUserBeforeCreate(Users users) {
        var user = usersRepository.findByUser(users.getUser());
        if(user != null) {
            throw new BusinessException(MessageUtil.EMAIL_ALREADY_EXIST);
        }
        return user;
    }

    public void encrypt(Users users) {
      users.setPassword(passwordEncoder.encode(users.getPassword()));
    }


}
