package com.javastudy.coworkings.security;

import java.security.NoSuchAlgorithmException;

public interface SecurityService {
    Session login(String name, String password);

    void logout(String token);

    Session getSession(String token);
}
