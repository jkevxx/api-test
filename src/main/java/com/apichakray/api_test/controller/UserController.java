package com.apichakray.api_test.controller;

import com.apichakray.api_test.dto.UserDTO;
import com.apichakray.api_test.entity.UserEntity;
import com.apichakray.api_test.exception.ApiRequestException;
import com.apichakray.api_test.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) String sortedBy) {
        try {
            return ResponseEntity.ok(userService.getAllUsers(sortedBy));
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }


    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User created successfully")
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserEntity userEntity) {
        try {
            return ResponseEntity.ok(userService.createUser(userEntity));
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "User updated successfully")
    public ResponseEntity<UserEntity> patchUser(@PathVariable Long id, @RequestBody @Valid UserEntity userEntity) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, userEntity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "User deleted successfully")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
