package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;

public interface CoworkingService {
    Coworking getById(long id);

    List<Coworking> getTop();

    List<Coworking> searchByName(String name);
}
