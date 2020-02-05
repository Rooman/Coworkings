package com.javastudy.coworkings.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String path;

    public PropertyReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            logger.error("Cant receive properties file on the path: {}", path);
            throw new RuntimeException("File with properties not found", e);
        } catch (IOException e) {
            logger.error("Cant read properties file on the path: {}", path);
            throw new RuntimeException("File with properties could not be read", e);
        }
        logger.info("Property file was successfully loaded: {}", path);
        return properties;
    }
}
