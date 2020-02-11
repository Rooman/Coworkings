package com.javastudy.coworkings.service.security;

import com.javastudy.coworkings.entity.Session;

public interface SecurityService {
    Session login(String name, String password);

    void logout(String token);

    Session getSession(String token);
}
