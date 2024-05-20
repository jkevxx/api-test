package com.apichakray.api_test.service;

import com.apichakray.api_test.entity.PhoneEntity;

import java.util.List;

public interface IPhoneService {

    List<PhoneEntity> getAllPhones();

    PhoneEntity getPhoneById(Long id);

    PhoneEntity createPhone(PhoneEntity phoneEntity);

    PhoneEntity updatePhone(PhoneEntity phoneEntity);

    void deletePhone(Long id);
}
