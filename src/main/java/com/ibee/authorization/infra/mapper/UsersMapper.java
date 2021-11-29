package com.ibee.authorization.infra.mapper;

import com.ibee.authorization.api.dto.UsersDTO;
import com.ibee.authorization.model.entities.Users;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UsersMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsersDTO toDTO(Users users) {
        return modelMapper.map(users, UsersDTO.class);
    }

    public Users toEntity(UsersDTO usersDTO) {
        return modelMapper.map(usersDTO, Users.class);
    }

    public List<UsersDTO> toDTOList(List<Users> users) {
        return modelMapper.map(users, new TypeToken<List<Users>>() {}.getType());
    }

    public List<Users> toEntityList(List<UsersDTO> usersDTO) {
        return modelMapper.map(usersDTO, new TypeToken<List<UsersDTO>>() {}.getType());
    }

    public Set<UsersDTO> toDTOSet(Set<UsersDTO> user) {
        return modelMapper.map(user, new TypeToken<Set<UsersDTO>>() {}.getType());
    }

    public Set<Users> toEntitySet(Set<UsersDTO> user) {
        return modelMapper.map(user, new TypeToken<Set<UsersDTO>>() {}.getType());
    }

//    public UsersDTO toDTO(Optional<Users> user) {
//        UsersDTO userDTO = new UsersDTO();
//        userDTO.setId(user.get().getId());
//        userDTO.setUser(user.get().getUser());
//        userDTO.setPassword(user.get().getPassword());
//        return userDTO;
//    }

//    public static UsersDTO toDTOOptional(Users user) {
//        UsersDTO userDTO = new UsersDTO();
//        userDTO.setId(user.getId());
//        userDTO.setUser(user.getUsers());
//        return userDTO;
//    }

    public void copyProperties(Optional<Users> user, UsersDTO userDTO) {
        userDTO.setId(user.get().getId());
//        userDTO.setUser(user.get().getUser());
    }

    public void copyProperties(UsersDTO usersDTO, Users users) {
        BeanUtils.copyProperties(usersDTO, users, "id");
    }

}
