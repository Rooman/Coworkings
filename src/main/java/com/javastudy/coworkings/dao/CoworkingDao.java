
package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.entity.CoworkingFilter;

import java.util.List;

public interface CoworkingDao {
    Coworking getById(long id);
    List<Coworking> getTop(int count);
    List<Coworking> searchByName(String name);
    List<Coworking> getFiltered (CoworkingFilter filters);
    List<Coworking> getByCity(String city);
}

