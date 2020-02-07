
package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.entity.Coworking;

import java.util.List;

public interface CoworkingDao {
    Coworking getById(long id);

    List<Coworking> getTop(int count);
  
    List<Coworking> searchByName(String name);
}

