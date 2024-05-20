package com.apichakray.api_test.repository;

import com.apichakray.api_test.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
