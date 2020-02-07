package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;

public interface CoworkingService {
    Coworking getById(long id);

    List<Coworking> getTopEight();
  
    List<Coworking> searchByName(String name);
}
