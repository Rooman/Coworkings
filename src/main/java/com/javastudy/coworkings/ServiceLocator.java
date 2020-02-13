package com.javastudy.coworkings;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.dao.jdbc.ConnectionFactory;
import com.javastudy.coworkings.dao.jdbc.JdbcCoworkingDao;
import com.javastudy.coworkings.dao.jdbc.JdbcUserDao;
import com.javastudy.coworkings.service.CoworkingService;
import com.javastudy.coworkings.service.security.DefaultSecurityService;
import com.javastudy.coworkings.service.security.SecurityService;
import com.javastudy.coworkings.service.UserService;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.service.impl.DefaultUserService;
import com.javastudy.coworkings.util.PropertyReader;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();
    private static final String PROPERTIES_FILE_LOCATION = "application.properties";

    static {
        PropertyReader propertyReader = new PropertyReader(PROPERTIES_FILE_LOCATION);
        Properties applicationProperties = propertyReader.getProperties();
        DataSource dataSource = ConnectionFactory.getDataSource(applicationProperties);

        CoworkingDao coworkingDao = new JdbcCoworkingDao(dataSource);
        register(CoworkingDao.class, coworkingDao);

        UserDao userDao = new JdbcUserDao(dataSource);
        register(UserDao.class, userDao);

        UserService userService = new DefaultUserService(userDao);
        register(UserService.class, userService);

        int expireDays = Integer.parseInt(applicationProperties.getProperty("session.expire.days"));
        SecurityService securityService = new DefaultSecurityService(userService, expireDays);

        register(SecurityService.class, securityService);

        Integer topCoworkingsCount = propertyReader.getInt("top.coworkings.count");
        CoworkingService coworkingService = new DefaultCoworkingService(coworkingDao, topCoworkingsCount);
        register(CoworkingService.class, coworkingService);
        register(PropertyReader.class, propertyReader);
    }

    public static void register(Class<?> serviceClass, Object service) {
        SERVICES.put(serviceClass, service);
    }

    public static <T> T getService(Class<T> serviceClass) {
        return serviceClass.cast(SERVICES.get(serviceClass));
    }
}
