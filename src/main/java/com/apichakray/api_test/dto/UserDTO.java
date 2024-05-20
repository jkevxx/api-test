package com.apichakray.api_test.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private List<String> phones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhone() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
