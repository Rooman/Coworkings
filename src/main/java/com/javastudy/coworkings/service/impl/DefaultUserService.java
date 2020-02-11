package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultUserService implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserDao userdao;

    public DefaultUserService(UserDao userdao) {
        this.userdao = userdao;
    }

    @Override
    public User getById(long id) {
        User user = userdao.getById(id);
        logger.info("Got user with id: {} from db", id);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = userdao.getByUsername(username);
        logger.info("Got user with username: {} from db", username);
        return user;
    }
}
