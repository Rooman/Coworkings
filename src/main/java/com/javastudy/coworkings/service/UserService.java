package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.User;

public interface UserService {
    User getById(long id);

    User getByUsername(String username);
}
