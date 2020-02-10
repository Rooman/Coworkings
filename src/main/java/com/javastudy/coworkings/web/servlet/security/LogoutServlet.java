package com.javastudy.coworkings.web.servlet.security;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.security.DefaultSecurityService;
import com.javastudy.coworkings.security.SecurityService;
import com.javastudy.coworkings.security.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private SecurityService securityService;

    public LogoutServlet() {
        securityService = ServiceLocator.getService(SecurityService.class);
    }


    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("user-token")) {
                    String userToken = cookie.getValue();
                    Session session = securityService.getSession(userToken);
                    if (session != null) {
                        securityService.logout(userToken);
                    }
                }
            }
        }

        resp.sendRedirect("/login");
    }
}
