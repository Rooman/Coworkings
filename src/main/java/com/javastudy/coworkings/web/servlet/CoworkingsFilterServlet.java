package com.javastudy.coworkings.web.servlet;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.entity.CoworkingFilter;
import com.javastudy.coworkings.entity.Session;
import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.CoworkingService;
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

public class CoworkingsFilterServlet extends HttpServlet{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);
    CoworkingFilter coworkingFilter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> parameters = new HashMap<>();
        String city = request.getParameter("cityName");
        parameters.put("city", city);

        coworkingFilter = new CoworkingFilter();
        coworkingFilter.setCity(city);

        List<String> filters = new ArrayList<>();
        boolean hasDesk = "true".equalsIgnoreCase(request.getParameter("desk"));
        parameters.put("hasDesk", hasDesk);
        if (hasDesk){
            filters.add("containsdesk");
        }

        boolean hasPrivateOffice = "true".equalsIgnoreCase(request.getParameter("privateOffice"));
        parameters.put("hasPrivateOffice", hasPrivateOffice);
        if (hasPrivateOffice){
            filters.add("containsoffice");
        }

        boolean hasMeetingRoom = "true".equalsIgnoreCase(request.getParameter("meetingRoom"));
        parameters.put("hasMeetingRoom", hasMeetingRoom);
        if (hasMeetingRoom){
            filters.add("containsmeetingroom");
        }
        coworkingFilter.setFilters(filters);


        List<String> price = new ArrayList<>();
        boolean below200 = "true".equalsIgnoreCase(request.getParameter("below200"));
        parameters.put("below200", below200);
        if (below200){
            filters.add("below200");
        }
        boolean below300 = "true".equalsIgnoreCase(request.getParameter("below300"));
        parameters.put("below300", below300);
        if (below300){
            filters.add("below300");
        }
        boolean above300 = "true".equalsIgnoreCase(request.getParameter("above300"));
        parameters.put("above300", above300);
        if (above300){
            filters.add("above300");
        }
        coworkingFilter.setPrice(price);

        Map<String, String > rating = new HashMap<>();
        String ratingOrder = request.getParameter("ratingOrder");
        parameters.put("ratingOrder", ratingOrder);
        rating.put("ratingOrder", ratingOrder);
        coworkingFilter.setRating(rating);


        List<String> equipment = new ArrayList<>();
        boolean singleMonitors = "true".equalsIgnoreCase(request.getParameter("singleMonitors"));
        parameters.put("hasSingleMonitors", singleMonitors);
        if (singleMonitors){
            filters.add("singleMonitors");
        }

        boolean dualMonitors = "true".equalsIgnoreCase(request.getParameter("dualMonitors"));
        parameters.put("hasDualMonitors", dualMonitors);
        if (dualMonitors){
            filters.add("dualMonitors");
        }

        boolean videoRec = "true".equalsIgnoreCase(request.getParameter("videoRec"));
        parameters.put("hasVideoRec", videoRec);
        if (videoRec){
            filters.add("videoRec");
        }

        boolean printer = "true".equalsIgnoreCase(request.getParameter("printer"));
        parameters.put("hasPrinter", printer);
        if (printer){
            filters.add("printer");
        }

        boolean scanner = "true".equalsIgnoreCase(request.getParameter("scanner"));
        parameters.put("hasScanner", scanner);
        if (scanner){
            filters.add("scanner");
        }

        boolean projector = "true".equalsIgnoreCase(request.getParameter("projector"));
        parameters.put("hasProjector", projector);
        if (projector){
            filters.add("projector");
        }

        boolean microphone = "true".equalsIgnoreCase(request.getParameter("microphone"));
        parameters.put("hasMicrophone", microphone);
        if (microphone){
            filters.add("microphone");
        }

        coworkingFilter.setEquipment(equipment);


        Session session = (Session) request.getAttribute("session");
        User user = null;
        if (session != null) {
            user = session.getUser();
        }
        parameters.put("user", user);
        logger.debug("User {}", user);


        List<Coworking> coworkings = coworkingService.getFiltered(coworkingFilter);
        parameters.put("coworkings", coworkings);
        logger.debug("Coworkings {}", coworkings);

        response.setContentType("text/html; charset=utf-8");
        ThymeleafTemplater.process("city", parameters,  response.getWriter());
    }
}
