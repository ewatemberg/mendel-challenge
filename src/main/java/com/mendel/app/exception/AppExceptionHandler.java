package com.mendel.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TypeNotFoundException.class})
    public ResponseEntity<ApiError> handleAccessDenied(TypeNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getApiError(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Missing request parameter", ex);
        ApiError apiError = new ApiError(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("Values entered are invalid", ex);
        ApiError apiError = new ApiError(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

}
