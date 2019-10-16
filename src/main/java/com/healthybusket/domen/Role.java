package com.healthybusket.domen;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    COURIER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
