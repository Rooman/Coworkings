package com.javastudy.coworkings.web.servlet;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.entity.Session;
import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.web.templater.ThymeleafTemplater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CoworkingPageServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Map<String, Object> parameters = new HashMap<>();

        Session session = (Session) req.getAttribute("session");
        User user = null;
        if (session != null) {
            user = session.getUser();
        }
        parameters.put("user", user);
        logger.debug("User {}", user);

        Coworking coworking = coworkingService.getById(id);
        parameters.put("coworking", coworking);

        response.setContentType("text/html; charset=utf-8");
        ThymeleafTemplater.process("coworking", parameters, response.getWriter());
    }
}
