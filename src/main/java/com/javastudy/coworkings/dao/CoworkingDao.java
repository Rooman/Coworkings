package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;

public interface CoworkingDao {
    public Coworking getById(long id);

    public List<Coworking> searchByName(String name);
}
