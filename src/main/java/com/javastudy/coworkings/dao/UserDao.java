package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.entity.User;

public interface UserDao {
    User getById(long id);

    User getByUsername(String username);
}
