package com.flyerssoft.org_chart.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {

//    @Autowired
//    private EndpointsListener endpointsListener;

    @Autowired
    private DispatcherServlet servlet;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized entry");
        log.error("403 Forbidden Error :{}", authException.toString());
        try {
            if (this.isEndpointExist(request)) {
    //            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(403);
            response.getWriter().print(buildObject());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject buildObject() {
        return new JSONObject().put("status", String.valueOf(403)).put("message", "Access denied due to insufficient authorization").put("state", "FORBIDDEN");
    }

    /*    Custom method to check the api(end point) whether is exist or not     */
    public boolean isEndpointExist(HttpServletRequest request) throws Exception {
        if (CollectionUtils.isNotEmpty(servlet.getHandlerMappings())) {
            for (HandlerMapping handlerMapping : Objects.requireNonNull(servlet.getHandlerMappings())) {
                HandlerExecutionChain foundHandler = handlerMapping.getHandler(request);
                if (ObjectUtils.isNotEmpty(foundHandler)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}