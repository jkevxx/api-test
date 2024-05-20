package com.apichakray.api_test.repository;

import com.apichakray.api_test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByOrderByEmail();
}
