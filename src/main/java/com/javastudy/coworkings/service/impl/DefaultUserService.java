package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.UserService;

public class DefaultUserService implements UserService {
    private UserDao userdao;

    public DefaultUserService(UserDao userdao) {
        this.userdao = userdao;
    }

    @Override
    public User getById(long id) {
        return userdao.getById(id);
    }
}
