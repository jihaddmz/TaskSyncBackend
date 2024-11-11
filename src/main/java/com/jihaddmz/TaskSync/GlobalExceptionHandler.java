package com.jihaddmz.TaskSync;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                fieldErrors.get(0).getDefaultMessage()
        );
        return createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Leverage Exception Handler frameworf for id not found Exception.
     *
     * @param ex      NoSuchElementException
     * @param request WebRequest
     * @return http response
     */
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        return createResponseEntity(pd, null, HttpStatus.NOT_FOUND, request);
    }

    /**
     * Leverage Exception Handler frameworf for id not found Exception.
     *
     * @param ex      NoSuchElementException
     * @param request WebRequest
     * @return http response
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        return createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Leverage Exception Handler frameworf for IllegalArgumentException Exceptions.
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        return createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Leverage Exception Handler frameworf for unexpected Exceptions.
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return createResponseEntity(pd, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

