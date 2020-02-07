package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;


public interface CoworkingService {
    public List<Coworking> searchByName(String name);

    public Coworking getById(long id);

    public List<Coworking> getTopEight();
}