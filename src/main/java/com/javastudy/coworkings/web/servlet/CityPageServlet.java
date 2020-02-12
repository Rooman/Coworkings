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
import java.util.List;
import java.util.Map;

public class CityPageServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        String city = req.getParameter("cityName");

        boolean hasDesk = "true".equalsIgnoreCase(req.getParameter("desk"));
        parameters.put("hasDesk", hasDesk);
        boolean hasPrivateOffice = "true".equalsIgnoreCase(req.getParameter("privateOffice"));
        parameters.put("hasPrivateOffice", hasPrivateOffice);
        boolean hasMeetingRoom = "true".equalsIgnoreCase(req.getParameter("meetingRoom"));
        parameters.put("hasMeetingRoom", hasMeetingRoom);

        parameters.put("city", city);

        Session session = (Session) req.getAttribute("session");
        User user = null;
        if (session != null) {
            user = session.getUser();
        }
        parameters.put("user", user);
        logger.debug("User {}", user);

        List<Coworking> coworkings = coworkingService.searchByCity(city);
        parameters.put("coworkings", coworkings);
        logger.debug("Coworkings {}", coworkings);

        response.setContentType("text/html; charset=utf-8");
        ThymeleafTemplater.process("city", parameters,  response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {

    }
}
