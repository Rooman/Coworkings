package com.javastudy.coworkings.web.servlet;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.web.templater.ThymeleafTemplater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        List<Coworking> coworkings = coworkingService.getTop();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("coworkings", coworkings);
        logger.debug("Coworkings {}", coworkings);

        response.setContentType("text/html; charset=utf-8");
        ThymeleafTemplater.process("index", parameters,  response.getWriter());
    }
}