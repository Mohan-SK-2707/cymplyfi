package com.flyerssoft.org_chart.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.flyerssoft.org_chart.exceptionhandler.AccessDeniedException;
import com.flyerssoft.org_chart.exceptionhandler.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {

    @Autowired
    private DispatcherServlet servlet;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("403 Forbidden Error :{}", authException.toString());
        if (this.isEndpointExist(request)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(403);
            response.getWriter().print(new ErrorResponse(403, false, "User Access denied due to Insufficient access"));
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User Access denied due to Insufficient access");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }
    }


    /*
    Custom method to check the api(end point) whether is exist or not
     */
    public boolean isEndpointExist(HttpServletRequest request) {
        for (HandlerMapping handlerMapping : Objects.requireNonNull(servlet.getHandlerMappings())) {
            try {
                HandlerExecutionChain foundHandler = handlerMapping.getHandler(request);
                if (foundHandler != null) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
