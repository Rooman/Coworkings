package com.javastudy.coworkings.web.servlet.security;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.security.DefaultSecurityService;
import com.javastudy.coworkings.security.SecurityService;
import com.javastudy.coworkings.security.Session;
import com.javastudy.coworkings.web.templater.ThymeleafTemplater;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LoginServlet extends HttpServlet {
    private SecurityService securityService;

    public LoginServlet() {
        securityService = ServiceLocator.getService(DefaultSecurityService.class);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        ThymeleafTemplater.process("login", resp.getWriter());
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Session session = securityService.login(login, password);
        if (session != null) {
            Cookie cookie = new Cookie("user-token", session.getToken());
            //One day in seconds:
            int secondsBeforeExpire = (int) (session.getExpiryTime().toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            cookie.setMaxAge(secondsBeforeExpire);
            resp.addCookie(cookie);
            resp.sendRedirect("/home");
            req.setAttribute("session", session);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
