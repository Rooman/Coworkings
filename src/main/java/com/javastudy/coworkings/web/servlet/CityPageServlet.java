package com.javastudy.coworkings.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        Map<String, Object> parameters = new HashMap<>();
        boolean below200 = "true".equalsIgnoreCase(req.getParameter("below200"));
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

    }
}
