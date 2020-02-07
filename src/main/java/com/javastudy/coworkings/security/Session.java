package com.javastudy.coworkings.security;

import com.javastudy.coworkings.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class Session {
    private String token;
    private User user;
    private LocalDateTime expiryTime;
}
