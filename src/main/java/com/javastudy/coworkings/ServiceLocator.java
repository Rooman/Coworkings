package com.javastudy.coworkings;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.jdbc.ConnectionFactory;
import com.javastudy.coworkings.dao.jdbc.JdbcCoworkingDao;
import com.javastudy.coworkings.util.PropertyReader;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        PropertyReader propertyReader = new PropertyReader("src/main/resources/application.properties");
        Properties applicationProperties = propertyReader.getProperties();
        DataSource myDataSource = ConnectionFactory.getDataSource(applicationProperties);

        CoworkingDao coworkingDao = new JdbcCoworkingDao(myDataSource);
        register(coworkingDao.getClass(), coworkingDao);
    }
    public static void register(Class<?> serviceClass, Object service){
        SERVICES.put(serviceClass, service);

    }

    public static <T> T getService(Class<T> serviceClass){
        return serviceClass.cast(SERVICES.get(serviceClass));
    }


}
