package com.crud.trello_mj.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/protegido/*")
public class FiltroSesionUtil implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // Verificar si hay una sesión activa
        if (session == null || session.getAttribute("usuario") == null) {
            // Redirigir al Login si no hay sesión
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login.xhtml");
        } else {
            // Continuar con la solicitud si hay sesión
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}