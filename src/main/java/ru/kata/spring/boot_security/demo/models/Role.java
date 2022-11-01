package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {

    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
