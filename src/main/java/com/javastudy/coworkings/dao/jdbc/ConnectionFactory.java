package com.javastudy.coworkings.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class ConnectionFactory {
    public static DataSource getDataSource(Properties properties) {
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
