package com.ibee.authorization.model.services.impl;

import com.ibee.authorization.model.exceptions.BusinessException;
import com.ibee.authorization.model.entities.Users;
import com.ibee.authorization.infra.repository.UsersRepository;
import com.ibee.authorization.model.exceptions.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsersLoadServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        var users = verifyUserExist(user);
        return new User(users.getEmail(), passwordEncoder.encode(users.getPassword()), Collections.emptyList());
    }

    private Users verifyUserExist(String user) {
        var users = usersRepository.findByEmail(user);
        if(user == null) {
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }
        return users;
    }

}
