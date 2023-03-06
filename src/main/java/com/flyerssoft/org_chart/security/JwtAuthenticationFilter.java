package com.flyerssoft.org_chart.security;

import com.flyerssoft.org_chart.utility.StoreRoleBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(403);
                response.getWriter().print(buildObject(response.getStatus(), "Authorization token is missing", "FORBIDDEN"));
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
//                throw new AccessDeniedException("Invalid Token");
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(403);
                response.getWriter().print(buildObject(404, "token doesn't have valid user details - " + userName, "NOT_FOUND"));
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = userDataService.loadUserByUsername(userName);
            } catch (Exception e) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(404);
                response.getWriter().print(buildObject(404, "User doesn't exist with this username - " + userName, "NOT_FOUND"));
            }
            if (ObjectUtils.isNotEmpty(userDetails) && CollectionUtils.isNotEmpty(userDetails.getAuthorities())) {
                storeRoleBean.role = userDetails.getAuthorities().iterator().next().getAuthority();
            }
            if (ObjectUtils.isNotEmpty(userDetails) && jwtTokenUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private JSONObject buildObject(Integer status, String errMsg, String state) {
        return new JSONObject().put("status", status).put("message", errMsg).put("state", state);
    }

}
