package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.service.CoworkingService;

import java.util.List;

public class DefaultCoworkingService implements CoworkingService {
    private CoworkingDao coworkingDao;
    private int topCoworkingsCount;

    public DefaultCoworkingService(CoworkingDao coworkingDao, int topCoworkingsCount) {
        this.coworkingDao = coworkingDao;
        this.topCoworkingsCount = topCoworkingsCount;
    }

    @Override
    public Coworking getById(long id) {
        return coworkingDao.getById(id);
    }

    @Override
    public List<Coworking> getTop() {
        return coworkingDao.getTop(topCoworkingsCount);
    }

    @Override
    public List<Coworking> searchByName(String name) {
        return coworkingDao.searchByName(name);
    }
}

