package com.flyerssoft.org_chart.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {

    @Autowired
    private DispatcherServlet servlet;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized entry");
        log.error("403 Forbidden Error :{}", authException.toString());
//        if (this.isEndpointExist(request)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(403);
            response.getWriter().print(buildObject());
//        }
    }

    private JSONObject buildObject() {
        return new JSONObject().put("status", String.valueOf(403)).put("message", "Access denied due to insufficient authorization").put("state", "FORBIDDEN");
    }

    /*    Custom method to check the api(end point) whether is exist or not     */
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