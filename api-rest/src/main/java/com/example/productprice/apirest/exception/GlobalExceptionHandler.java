package com.example.productprice.apirest.exception;

import com.example.productprice.domain.exception.ProductPriceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingParameter(MissingServletRequestParameterException ex) {
        String detail = "Request parameter " + ex.getParameterName() + " is missing";
        log.warn(detail);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String requiredType = Optional.ofNullable(ex.getRequiredType())
                .map(Class::getSimpleName)
                .orElse("unknown");
        String detail = "Request parameter " + ex.getName() + " has invalid value. Expected type: " + requiredType;
        log.warn(detail);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
        ConstraintViolation<?> violation = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .orElse(null);
        String param = Optional.ofNullable(violation)
                .map(v -> StreamSupport.stream(v.getPropertyPath().spliterator(), false)
                        .map(Path.Node::getName)
                        .reduce((first, second) -> second)
                        .orElse("unknown"))
                .orElse("unknown");
        String detail = Optional.ofNullable(violation)
                .map(v -> v.getConstraintDescriptor().getAnnotation())
                .filter(a -> a instanceof Min)
                .map(a -> "Request parameter " + param + " must be min " + ((Min) a).value())
                .orElse("Request parameter " + param + " is invalid");
        log.warn(detail);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    /**
     * Handler for IllegalArgumentException thrown by the service when invoked internally.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        log.warn(ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ProblemDetail handleProductPriceNotFound(ProductPriceNotFoundException ex) {
        log.warn(ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        String detail = "Unexpected error: " + ex.getMessage();
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, detail);
    }
}
