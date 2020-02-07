package com.javastudy.coworkings.web.servlet;


import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.service.impl.DefaultCoworkingService;
import com.javastudy.coworkings.web.templater.PageGenerator;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePgeServlet extends HttpServlet {

    private PageGenerator pageGenerator = ServiceLocator.getService(PageGenerator.class);
    private DefaultCoworkingService coworkingService = ServiceLocator.getService(DefaultCoworkingService.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext webContext = new WebContext(request, response, request.getServletContext());
        List<Coworking> coworkings = coworkingService.getTopEight();
        Map<String, Object> pageProperty = new HashMap<>();
        pageProperty.put("topCoworkings", coworkings);
        webContext.setVariables(pageProperty);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().println(pageGenerator.getPage("index.html", webContext));
    }

}

