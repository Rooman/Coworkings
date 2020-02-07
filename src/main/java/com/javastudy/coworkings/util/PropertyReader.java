package com.javastudy.coworkings.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String path;
    private Properties properties;

    public PropertyReader(String path) {
        this.path = path;
        properties = readProperties();
    }

    public String getString(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public Integer getInt(String propertyName) {
        String strProperty = getString(propertyName);

        return strProperty == null ? null : Integer.valueOf(strProperty);
    }

    public Properties getProperties() {
        return new Properties(properties);
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(path);
            properties.load(resourceAsStream);
        } catch (IOException e) {
            logger.error("Cant read properties file on the path: {}", path);
            throw new RuntimeException("File with properties could not be read", e);
        }
        logger.info("Property file was successfully loaded: {}", path);
        return properties;
    }
}
