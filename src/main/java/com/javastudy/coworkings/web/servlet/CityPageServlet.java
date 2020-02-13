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

public class CityPageServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        private final Logger logger = LoggerFactory.getLogger(getClass());
        private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);

        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse response) throws IOException {
            Map<String, Object> parameters = new HashMap<>();
            String city = req.getParameter("cityName");

            boolean hasDesk = Boolean.parseBoolean(req.getParameter("desk"));
            parameters.put("hasDesk", hasDesk);
            boolean hasPrivateOffice = Boolean.parseBoolean(req.getParameter("privateOffice"));
            parameters.put("hasPrivateOffice", hasPrivateOffice);
            boolean hasMeetingRoom = Boolean.parseBoolean(req.getParameter("meetingRoom"));
            parameters.put("hasMeetingRoom", hasMeetingRoom);

            boolean below200 = "true".equalsIgnoreCase(req.getParameter("below200"));//todo - rework to Boolean
            parameters.put("below200", below200);
            boolean below300 = "true".equalsIgnoreCase(req.getParameter("below300"));
            parameters.put("below300", below300);
            boolean above300 = "true".equalsIgnoreCase(req.getParameter("above300"));
            parameters.put("above300", above300);
            String ratingOrder = req.getParameter("ratingOrder");
            parameters.put("ratingOrder", ratingOrder);
            boolean singleMonitors = "true".equalsIgnoreCase(req.getParameter("singleMonitors"));
            parameters.put("hasSingleMonitors", singleMonitors);
            boolean dualMonitors = "true".equalsIgnoreCase(req.getParameter("dualMonitors"));
            parameters.put("hasDualMonitors", dualMonitors);
            boolean videoRec = "true".equalsIgnoreCase(req.getParameter("videoRec"));
            parameters.put("hasVideoRec", videoRec);
            boolean printer = "true".equalsIgnoreCase(req.getParameter("printer"));
            parameters.put("hasPrinter", printer);
            boolean scanner = "true".equalsIgnoreCase(req.getParameter("scanner"));
            parameters.put("hasScanner", scanner);
            boolean projector = "true".equalsIgnoreCase(req.getParameter("projector"));
            parameters.put("hasProjector", projector);
            boolean microphone = "true".equalsIgnoreCase(req.getParameter("microphone"));
            parameters.put("hasMicrophone", microphone);

            parameters.put("city", city);

            Session session = (Session) req.getAttribute("session");
            User user = null;
            if (session != null) {
                user = session.getUser();
            }
            parameters.put("user", user);
            logger.debug("User {}", user);

            List<Coworking> coworkings = coworkingService.getByCity(city);
            parameters.put("coworkings", coworkings);
            logger.debug("Coworkings {}", coworkings);

            response.setContentType("text/html; charset=utf-8");
            ThymeleafTemplater.process("city", parameters, response.getWriter());
        }
    }
}
