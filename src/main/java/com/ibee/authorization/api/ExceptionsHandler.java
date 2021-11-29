package com.ibee.authorization.api;

import com.ibee.authorization.api.dto.ResponseBody;
import com.ibee.authorization.api.dto.enums.ErrorType;
import com.ibee.authorization.model.exceptions.BusinessException;
import com.ibee.authorization.util.MessageUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessException(BusinessException e, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        ErrorType problem = ErrorType.EMAIL_ALREADY_EXIST;
        String detail = e.getMessage();
        ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
        return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
    }

    private ResponseBody.ResponseBodyBuilder createBuilderResponseBody(HttpStatus statusCode, ErrorType problemType, String detail) {
        return ResponseBody.builder()
                .status(statusCode.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(LocalDateTime.now());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
