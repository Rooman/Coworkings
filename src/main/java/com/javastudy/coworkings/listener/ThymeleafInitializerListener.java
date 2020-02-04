package com.javastudy.coworkings.listener;

import com.javastudy.coworkings.web.templater.ThymeleafTemplater;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThymeleafInitializerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext servletContext = sce.getServletContext();
        ThymeleafTemplater.configureNewInstance(servletContext);
    }
}
