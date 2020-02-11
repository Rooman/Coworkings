package com.javastudy.coworkings.entity;

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
