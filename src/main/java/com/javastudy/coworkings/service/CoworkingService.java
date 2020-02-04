package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;

public interface CoworkingService {
    public Coworking getById(long id);

    public List<Coworking> getTopEight();
  
    public List<Coworking> searchByName(String name);
}
