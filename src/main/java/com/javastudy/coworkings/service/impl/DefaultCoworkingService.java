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
    public List<Coworking> getByCity(String city) {
        return coworkingDao.getByCity(city);
    }

    @Override
    public List<Coworking> getTop() {
        List<Coworking> top = coworkingDao.getTop(topCoworkingsCount);
        if (top.size() < topCoworkingsCount) {
            int left = topCoworkingsCount - top.size();
            int startIndex = (int) (Math.random() * left);
            top.addAll(top.subList(startIndex, startIndex + left));
        }
        return top;
    }

    @Override
    public List<Coworking> searchByName(String name) {
        return coworkingDao.searchByName(name);
    }
}

