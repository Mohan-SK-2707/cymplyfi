//package com.flyerssoft.org_chart.security;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.List;
//import java.util.Map;
//
//public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        ApplicationContext applicationContext = event.getApplicationContext();
//        Map<RequestMappingInfo, HandlerMethod> endpoints = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
//        System.out.println(endpoints);
//
//    }
//}
