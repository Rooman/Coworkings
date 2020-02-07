package com.javastudy.coworkings;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.jdbc.ConnectionFactory;
import com.javastudy.coworkings.dao.jdbc.JdbcCoworkingDao;
import com.javastudy.coworkings.util.PropertyReader;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.web.templater.PageGenerator;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        PropertyReader propertyReader = new PropertyReader("src/main/resources/application.properties");
        Properties applicationProperties = propertyReader.getProperties();

        DataSource dataSource = ConnectionFactory.getDataSource(applicationProperties);

        CoworkingDao coworkingDao = new JdbcCoworkingDao(dataSource);
        register(coworkingDao.getClass(), coworkingDao);

        PageGenerator pageGenerator = PageGenerator.instance();
        register(pageGenerator.getClass(), pageGenerator);

        DefaultCoworkingService defaultCoworkingService = new DefaultCoworkingService(coworkingDao);
        register(DefaultCoworkingService.class, defaultCoworkingService);



    }
    public static void register(Class<?> serviceClass, Object service){
        SERVICES.put(serviceClass, service);

    }

    public static <T> T getService(Class<T> serviceClass){
        return serviceClass.cast(SERVICES.get(serviceClass));
    }


}
