package com.trackline.fitrockr.userservice.exception;

import com.trackline.fitrockr.userservice.model.ResponseErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class RequestExceptionHandler
{
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseErrorModel> handleRequestExceptions(Exception ex)
    {
        HttpStatusCode status;
        String message;

        if (ex instanceof ResponseStatusException rsEx) {
            status = rsEx.getStatusCode();
            message = rsEx.getReason();
            log.debug("handled exception with response {}", message, ex);
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            log.error("unhandled exception", ex);
        }

        ResponseErrorModel response = new ResponseErrorModel();
        response.setErrorMessage(message);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<Object> handleClientAbort(ClientAbortException ex, WebRequest request)
    {
        log.debug("request aborted by client: {}", request.getDescription(true));
        return null; // nothing to return on a closed connection
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseErrorModel> handleTypeMismatch(MethodArgumentTypeMismatchException ex)
    {
        return handleRequestExceptions(new BadRequestException(ex.getValue() + " is not a valid value for " + ex.getName()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseErrorModel> handleHttpMessageNotReadable(HttpMessageNotReadableException ex)
    {
        return handleRequestExceptions(new BadRequestException(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseErrorModel> handleMissingServletRequestParameter(MissingServletRequestParameterException ex)
    {
        return handleRequestExceptions(new BadRequestException(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        return handleRequestExceptions(new BadRequestException(ex.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseErrorModel> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex)
    {
        return handleRequestExceptions(new BadRequestException("body must be in valid json format"));
    }

}
