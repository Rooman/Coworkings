package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.service.CoworkingService;

import java.util.List;

public class DefaultCoworkingService implements CoworkingService {

    private CoworkingDao coworkingDao;

    public DefaultCoworkingService(CoworkingDao coworkingDao) {
        this.coworkingDao = coworkingDao;
    }

    @Override
    public List<Coworking> searchByName(String name) {
        return coworkingDao.searchByName(name);
    }
}