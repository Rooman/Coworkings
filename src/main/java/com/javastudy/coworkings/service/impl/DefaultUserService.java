package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.UserService;

public class DefaultUserService implements UserService {
    /* Proposed potential code for future while UserDao is implemented
    private UserDao userdao;

    public DefaultUserService(UserDao userdao) {
        this.userdao = userdao;
    }*/

    @Override
    public User getById(long id) {
        //return userdao.getById(id);
        return null;
    }
}
