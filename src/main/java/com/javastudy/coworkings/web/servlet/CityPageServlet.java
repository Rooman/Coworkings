package com.javastudy.coworkings.web.servlet;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.*;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.web.templater.ThymeleafTemplater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        parameters.put("city", city);

        CoworkingFilter coworkingFilter = new CoworkingFilter();
        coworkingFilter.setCity(city);

        List<String> filters = new ArrayList<>();
        boolean hasDesk = Boolean.parseBoolean(req.getParameter("desk"));
        parameters.put("hasDesk", hasDesk);
        if (hasDesk) {
            filters.add("containsdesk");
        }

        boolean hasPrivateOffice = Boolean.parseBoolean(req.getParameter("privateOffice"));
        parameters.put("hasPrivateOffice", hasPrivateOffice);
        if (hasPrivateOffice) {
            filters.add("containsoffice");
        }

        boolean hasMeetingRoom = Boolean.parseBoolean(req.getParameter("meetingRoom"));
        parameters.put("hasMeetingRoom", hasMeetingRoom);
        if (hasMeetingRoom) {
            filters.add("containsmeetingroom");
        }
        coworkingFilter.setFilters(filters);


        List<String> price = new ArrayList<>();
        boolean below200 = Boolean.parseBoolean(req.getParameter("below200"));//todo - rework to Boolean
        parameters.put("below200", below200);
        if (below200) {
            price.add("100-200");
        }

        boolean below300 = Boolean.parseBoolean(req.getParameter("below300"));
        parameters.put("below300", below300);
        if (below300) {
            price.add("200-300");
        }

        boolean above300 = Boolean.parseBoolean(req.getParameter("above300"));
        parameters.put("above300", above300);
        if (above300) {
            price.add("above300");
        }
        coworkingFilter.setPrice(price);


        Map<String, RatingOrder> rating = new HashMap<>();
        String ratingOrder = req.getParameter("ratingOrder");
        parameters.put("ratingOrder", ratingOrder);
        rating.put("ratingOrder", RatingOrder.getByName(ratingOrder));
        coworkingFilter.setRating(rating);

        List<String> equipment = new ArrayList<>();
        boolean singleMonitors = Boolean.parseBoolean(req.getParameter("singleMonitors"));
        parameters.put("hasSingleMonitors", singleMonitors);
        if (singleMonitors) {
            equipment.add("hasSingleMonitors");
        }

        boolean dualMonitors = Boolean.parseBoolean(req.getParameter("dualMonitors"));
        parameters.put("hasDualMonitors", dualMonitors);
        if (dualMonitors) {
            equipment.add("hasDualMonitors");
        }

        boolean videoRec = Boolean.parseBoolean(req.getParameter("videoRec"));
        parameters.put("hasVideoRec", videoRec);
        if (videoRec) {
            equipment.add("hasVideoRec");
        }

        boolean printer = Boolean.parseBoolean(req.getParameter("printer"));
        parameters.put("hasPrinter", printer);
        if (printer) {
            equipment.add("hasPrinter");
        }

        boolean scanner = Boolean.parseBoolean(req.getParameter("scanner"));
        parameters.put("hasScanner", scanner);
        if (scanner) {
            equipment.add("hasScanner");
        }

        boolean projector = Boolean.parseBoolean(req.getParameter("projector"));
        parameters.put("hasProjector", projector);
        if (projector) {
            equipment.add("hasProjector");
        }

        boolean microphone = Boolean.parseBoolean(req.getParameter("microphone"));
        parameters.put("hasMicrophone", microphone);
        if (microphone) {
            equipment.add("hasMicrophone");
        }
        coworkingFilter.setEquipment(equipment);

        Session session = (Session) req.getAttribute("session");
        User user = null;
        if (session != null) {
            user = session.getUser();
        }
        parameters.put("user", user);
        logger.debug("User {}", user);

        //List<Coworking> coworkings = coworkingService.getByCity(city);//todo - method getFiltered should take city, coworkingFilter
        List<Coworking> coworkings = coworkingService.getFiltered(coworkingFilter);
        parameters.put("coworkings", coworkings);
        logger.debug("Coworkings {}", coworkings);

        response.setContentType("text/html; charset=utf-8");
        ThymeleafTemplater.process("city", parameters, response.getWriter());
    }
}

