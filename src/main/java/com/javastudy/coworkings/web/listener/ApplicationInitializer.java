package com.javastudy.coworkings.web.listener;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.util.PropertyReader;
import com.javastudy.coworkings.web.templater.ThymeleafTemplater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializer implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("Initializing Application");
        ServletContext servletContext = event.getServletContext();
        PropertyReader propertyReader = ServiceLocator.getService(PropertyReader.class);
        ThymeleafTemplater.configTemplate(servletContext, propertyReader.getProperties());
    }
}