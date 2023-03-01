package com.flyerssoft.org_chart.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends CustomExceptionHandler {

    public AccessDeniedException(String message) {
        super(message);
    }

}
