package com.javastudy.coworkings.entity;

import lombok.*;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class User {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private UserRole userRole;
    private String tel;
    private String email;
    private String city;
    private String sole;
}
