package com.flyerssoft.org_chart.security;

import com.flyerssoft.org_chart.utility.StoreRoleBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    UserDataService userDataService;

    @Autowired
    StoreRoleBean storeRoleBean;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (ObjectUtils.isEmpty(request.getHeader("Authorization"))) {
            if (request.getMethod().equalsIgnoreCase("PUT") || request.getMethod().equalsIgnoreCase("GET") || request.getMethod().equalsIgnoreCase("DELETE")) {
                logger.error("Token Is missing");
            }
        }

        String header = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            userName = jwtTokenUtils.getUsernameFromToken(token);
            if (ObjectUtils.isEmpty(userName)) {
                logger.error("Invalid Token");
                throw new AccessDeniedException("Invalid Token");
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDataService.loadUserByUsername(userName);// if token is valid configure Spring Security to manually set// authentication
            storeRoleBean.role = userDetails.getAuthorities().iterator().next().getAuthority();
            if (jwtTokenUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));// After setting the Authentication in the context, we specify// that the current user is authenticated. So it passes the// Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
