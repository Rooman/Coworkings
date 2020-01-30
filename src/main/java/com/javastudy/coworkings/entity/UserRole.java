package com.javastudy.coworkings.entity;

public enum UserRole {
    GUEST("GUEST"),
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole getByName(String name) {
        for (UserRole userRole : values()) {
            if (userRole.name.equalsIgnoreCase(name)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No UserRole with such name: " + name);
    }
}
