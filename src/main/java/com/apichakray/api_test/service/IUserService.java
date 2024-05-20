package com.apichakray.api_test.service;

import com.apichakray.api_test.dto.UserDTO;
import com.apichakray.api_test.entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUsers(String sortedBy);

    UserDTO getUserById(Long id);

    UserEntity createUser(UserEntity userEntity);

    UserEntity updateUser(Long id, UserEntity userEntity);

    void deleteUser(Long id);
}
