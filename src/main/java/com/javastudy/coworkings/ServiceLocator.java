package com.javastudy.coworkings;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.jdbc.JdbcCoworkingDao;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {

        CoworkingDao coworkingDao = new JdbcCoworkingDao();
        register(coworkingDao.getClass(), coworkingDao);



    }
    public static void register(Class<?> serviceClass, Object service){
        SERVICES.put(serviceClass, service);
    }

    public static <T> T getService(Class<T> serviceClass){
        return serviceClass.cast(SERVICES.get(serviceClass));
    }


}
