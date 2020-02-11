package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.CoworkingDao;

import com.javastudy.coworkings.entity.Coworking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcCoworkingDaoTest {

    @Test
    public void testSearchByName() {
        CoworkingDao coworkingDao = ServiceLocator.getService(CoworkingDao.class);
        List<Coworking> listOfCoworkings = coworkingDao.searchByName("Crea");
        assertEquals(4, listOfCoworkings.size());
    }
}
