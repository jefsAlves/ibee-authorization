package com.ibee.authorization.model.services.impl;

import com.ibee.authorization.api.dto.UserPasswordDTO;
import com.ibee.authorization.api.dto.UsersDTO;
import com.ibee.authorization.api.exceptions.CannotDeleteException;
import com.ibee.authorization.api.exceptions.IdNotFoundException;
import com.ibee.authorization.infra.mapper.UsersMapper;
import com.ibee.authorization.infra.repository.UsersRepository;
import com.ibee.authorization.model.entities.Users;
import com.ibee.authorization.model.exceptions.BusinessException;
import com.ibee.authorization.model.exceptions.ExceptionMessage;
import com.ibee.authorization.model.services.UsersService;
import com.ibee.authorization.util.MessageUtil;
import com.ibee.authorization.util.UsersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper mapper;

    @Autowired
    private UsersUtil userUtil;

//    @Override
//    public Users search(Long userId) {
//        Optional<Users> user = usersRepository.findById(userId);
//        return user.orElseThrow(() -> new IdNotFoundException(MessageUtil.ID_NOT_FOUND));
//    }

    @Override
    public UsersDTO searchUsers(Long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        users.orElseThrow(() -> new IdNotFoundException(MessageUtil.ID_NOT_FOUND));
        UsersDTO usersDTO = new UsersDTO();
        mapper.copyProperties(users, usersDTO);
        return usersDTO;
    }

    @Override
    public List<UsersDTO> listsUsers() {
        return mapper.toDTOList(usersRepository.findAll());
    }

    @Transactional
    @Override
    public UsersDTO updateUsers(Long userId, UsersDTO userDTO) {
        var users = userUtil.verifyUserExist(userId);
        mapper.copyProperties(userDTO, users);
        usersRepository.save(users);
        return userDTO;
    }

    @Override
    @Transactional
    public UsersDTO createUser(UsersDTO usersDTO) {
        var user = mapper.toEntity(usersDTO);
        userUtil.validateUser(user);
        usersRepository.save(user);
        return new UsersDTO(usersDTO.getUser());
    }

    @Transactional
    @Override
    public void updatePassword(Long userId, UserPasswordDTO userPassword) {
        var user = userUtil.verifyUserExist(userId);
        if (!user.verifyPasswordCurrent(userPassword.getPasswordCurrent())) {
            throw new BusinessException(MessageUtil.INVALID_PASSWORD);
        }
        user.setPassword(userPassword.getPasswordNew());
    }

    @Transactional
    @Override
    public void deleteUsers(Long userId) {
        try {
            usersRepository.deleteById(userId);
        }
        catch (DataIntegrityViolationException e) {
            throw new CannotDeleteException(MessageUtil.CANNOT_DELETE);
        }
        catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(MessageUtil.ID_NOT_FOUND);
        }
    }

}