package com.javastudy.coworkings.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


public class AssetServlet extends HttpServlet {
    private static final int BUFFER_SIZE = 8192;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI().substring(1);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(requestURI)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int numBytesRead;
            while ((numBytesRead = inputStream.read(buffer)) > 0) {
                resp.getOutputStream().write(buffer, 0, numBytesRead);
            }
            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException("Unable to get needed resource by the request: " + requestURI);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException("Unable to get needed resource by the request: " + requestURI, e);
        }
    }
}