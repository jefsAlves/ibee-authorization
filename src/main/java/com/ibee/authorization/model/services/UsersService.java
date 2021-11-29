package com.ibee.authorization.model.services;

import com.ibee.authorization.api.dto.UserPasswordDTO;
import com.ibee.authorization.api.dto.UsersDTO;
import com.ibee.authorization.model.entities.Users;
import com.ibee.authorization.model.exceptions.BusinessException;

import java.util.List;

public interface UsersService {

    UsersDTO searchUsers(Long userId);

    List<UsersDTO> listsUsers();

    UsersDTO createUser(UsersDTO usersDTO);

    UsersDTO updateUsers(Long user, UsersDTO userDTO);

    void updatePassword(Long userId, UserPasswordDTO userPasswordDTO);

    void deleteUsers(Long user);

}