package com.apichakray.api_test.service;

import com.apichakray.api_test.dto.UserDTO;
import com.apichakray.api_test.entity.PhoneEntity;
import com.apichakray.api_test.entity.UserEntity;
import com.apichakray.api_test.repository.IPhoneRepository;
import com.apichakray.api_test.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Override
    public List<UserDTO> getAllUsers(String sortedBy) {
        List<UserEntity> users;

        if (sortedBy != null) {
            switch (sortedBy) {
                case "email":
                    users = userRepository.findAllByOrderByEmail();
                    break;
                default:
                    users = userRepository.findAll();
                    break;
            }
        } else {
            users = userRepository.findAll();
        }

        if (users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }

        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);

    }

    @Transactional
    public UserEntity createUser(UserEntity userEntity) {

        UserEntity user = new UserEntity();
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("The email is duplicated");
        }

        if(userEntity.getPhones() == null || userEntity.getPhones().isEmpty()) {
            throw new RuntimeException("The phones list cannot be empty");
        }

        createUserPhones(user, userEntity.getPhones());

        return user;
    }

    public void createUserPhones(UserEntity user, List<PhoneEntity> newPhones) {
        List<PhoneEntity> phones = new ArrayList<>();
        for (PhoneEntity phoneNumber : newPhones) {

            PhoneEntity phone = new PhoneEntity();
            phone.setPhone(phoneNumber.getPhone());
            phone.setUserEntity(user);
            phones.add(phone);
        }
        try {
            phoneRepository.saveAll(phones);
        } catch (Exception e) {
            throw new RuntimeException("The phones are duplicated");
        }

        user.setPhones(phones);
    }

    @Override
    public UserEntity updateUser(Long id,UserEntity userEntity) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        updateUserPhones(user, userEntity.getPhones());

        return userRepository.save(user);
    }

    public void updateUserPhones(UserEntity user, List<PhoneEntity> newPhones) {

        phoneRepository.deleteAll(user.getPhones());
        user.getPhones().clear();

        if (newPhones != null) {
            for (PhoneEntity phone : newPhones) {
                phone.setUserEntity(user);
                user.getPhones().add(phone);
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private UserDTO convertToDTO(UserEntity userEntity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());

        List<String> phonesDTO = new ArrayList<>();

        if (userEntity.getPhones() != null && !userEntity.getPhones().isEmpty()) {
            userEntity.getPhones().forEach(phoneEntity -> {
                phonesDTO.add(phoneEntity.getPhone());
            });
        }
        userDTO.setPhones(phonesDTO);

        return userDTO;
    }
}
