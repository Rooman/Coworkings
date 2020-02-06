package com.javastudy.coworkings.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
