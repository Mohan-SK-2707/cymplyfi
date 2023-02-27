package com.flyerssoft.org_chart.security;

import com.flyerssoft.org_chart.exceptionhandler.NotFoundException;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import com.flyerssoft.org_chart.repo.EmployeeDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDataService implements UserDetailsService {

    @Autowired
    private EmployeeDetailRepository employeeDetailRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeePersonalDetails user = employeeDetailRepository.findByEmail(username);
        log.info("User from loadbyusername method : {}",user);
        if (ObjectUtils.isNotEmpty(user)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        } else {
            log.error("User not found : {}",username);
            throw new UsernameNotFoundException("User doesn't exist with this username - " + username);
        }
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException ex) {
            throw new Exception(ex.toString());
        }
    }
}
