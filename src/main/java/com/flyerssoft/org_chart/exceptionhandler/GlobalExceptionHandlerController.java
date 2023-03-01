package com.flyerssoft.org_chart.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFound(NotFoundException ex) {
        return new ErrorResponse(404,false, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleBadCredential(BadCredentialException ex) {
        return new ErrorResponse(403,false, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse customValidation(MethodArgumentNotValidException e) {
        ErrorResponse apiError = new ErrorResponse();
        apiError.setStatus(400);
        apiError.setSuccess(false);
        String errorMessage = "Request has Invalid input.";
        Optional<FieldError> error = e.getBindingResult().getFieldErrors().stream().findFirst();
        if (error.isPresent()) {
            errorMessage = error.get().getDefaultMessage();
        }
        apiError.setMessage(errorMessage);
        return apiError;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse notReadableMessageValidation(HttpMessageNotReadableException ex) {
        return new ErrorResponse(400,false, ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse resourceAlreadyExistException(ResourceAlreadyExistsException ex) {
        return new ErrorResponse(406,false, ex.getMessage());
    }

    @ExceptionHandler(AddressAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse addressNotAcceptableState(AddressAlreadyExistException ex){
        return new ErrorResponse(406,false, ex.getMessage());
    }

    @ExceptionHandler(FieldException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse addressNotAcceptableState(FieldException ex) {
        return new ErrorResponse(406, false, ex.getMessage());
    }


}
