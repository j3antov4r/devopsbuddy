package com.devopsbuddy.enums;

import lombok.Getter;

public enum RolesEnum {

    BASIC(1, "ROLE_BASIC"),
    PRO(2, "ROLE_PRO"),
    ADMIN(3,"ROLE_ADMIN");

    @Getter
    private final int id;
    @Getter
    private final String roleName;

    RolesEnum(int id, String role) {
        this.id = id;
        this.roleName = role;
    }
}
