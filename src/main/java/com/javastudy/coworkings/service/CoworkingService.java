package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;


public interface CoworkingService {
    Coworking getById(long id);
    List<Coworking> getByCity(String city);
    List<Coworking> getTop();
    List<Coworking> searchByName(String name);
}
