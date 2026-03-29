package com.dev.farmmanager.controller.base;

import com.dev.farmmanager.exception.handler.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.util.List;

// credits for handler design: Gabriel Cismoski

@Slf4j
public class BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        final List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String[] messages = new String[errors.size()];
        for (int i = 0; i < errors.size(); i++) {
            messages[i] = errors.get(i).getDefaultMessage();
        }
        String finalMessage = String.join("; ", messages);

        log.error("MethodArgumentNotValidException - {}", finalMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), finalMessage, request.getRequestURI()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("AccessDeniedException");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError(Instant.now(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        final String message = e.getConstraintViolations().iterator().next().getMessage();
        log.error("ConstraintViolationException - {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, request.getRequestURI()));
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleHibernateConstraintViolationException(org.hibernate.exception.ConstraintViolationException e,
                                                                                HttpServletRequest request) {
        log.error(
                "HibernateConstraintViolationException - {}",
                e.getConstraintName() + ": " + e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getConstraintName() + ": " + e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error("IllegalArgumentException - {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleConversionFailedException(ConversionFailedException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(final BaseException e, final HttpServletRequest request) {
        log.error("BaseException - {}", e.getMessage());
        return ResponseEntity.status(e.getStatus())
                .body(new ApiError(Instant.now(), e.getStatus().value(), e.getStatus().getReasonPhrase(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There was an error processing the request body.")
    public void handleMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        log.error("Unable to bind post data sent to: {}\nCaught Exception:\n{}", request.getRequestURI(), exception.getMessage());
    }
}
