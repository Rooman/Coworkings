package com.javastudy.coworkings.web.servlet.security;

import com.javastudy.coworkings.web.templater.ThymeleafTemplater;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        ThymeleafTemplater.process("login", resp.getWriter());
    }

}
