package com.kodilla.ecommercee.config.security;

import java.util.stream.Stream;

public enum Role {
    USER,
    ADMIN;

    public static boolean exist(String role) {
        return Stream.of(Role.values())
                .map(Enum::name)
                .anyMatch(i -> i.equals(role));
    }
}
