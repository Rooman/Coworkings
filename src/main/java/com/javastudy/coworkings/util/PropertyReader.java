package com.javastudy.coworkings.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    String path;

    public PropertyReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {

        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with properties not found", e);
        } catch (IOException e) {
            throw new RuntimeException("File with properties could not be read", e);
        }
        return properties;
    }
}
