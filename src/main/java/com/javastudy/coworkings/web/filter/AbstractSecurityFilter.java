package com.javastudy.coworkings.web.filter;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.entity.UserRole;
import com.javastudy.coworkings.security.DefaultSecurityService;
import com.javastudy.coworkings.security.SecurityService;
import com.javastudy.coworkings.security.Session;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public abstract class AbstractSecurityFilter implements Filter {
    private SecurityService securityService;

    public AbstractSecurityFilter() {
        securityService = ServiceLocator.getService(DefaultSecurityService.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();
        boolean isAuth = false;
        UserRole userRole = UserRole.GUEST;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("user-token")) {
                    String token = cookie.getValue();
                    Session session = securityService.getSession(token);
                    if (session != null) {
                        userRole = session.getUser().getUserRole();
                        if (getAcceptedRoles().contains(userRole)) {
                            isAuth = true;
                            req.setAttribute("session", session);
                        }
                    }
                    break;
                }
            }
        }

        /*if (isAuth) {
            chain.doFilter(request, response);
        } else if (userRole == UserRole.GUEST) {
            chain.doFilter(request, response);
        }*/
        //Since currently both USER and GUEST both can access main & login pages, there no need create any special
        //redirect or extra conditions:
        //TODO: add redirection for pages where GUEST or USER don't have access (after more pages and ADMIN users added). Nomarch

        chain.doFilter(request, response);
    }

    abstract Set<UserRole> getAcceptedRoles();

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
