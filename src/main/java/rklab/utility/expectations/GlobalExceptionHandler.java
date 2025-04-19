package rklab.utility.expectations;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import rklab.utility.dto.ApiOutput;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {ServerException.class, HttpMessageNotReadableException.class
            , MethodArgumentNotValidException.class})
    public ApiOutput<?> handleInvalidInputs(Exception ex, WebRequest request){
        return new ApiOutput<String>(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ApiOutput<?> handleAccessDeniedHandler(Exception ex, WebRequest request){
        return new ApiOutput<String>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {InvalidInputException.class, BadRequestException.class})
    public ApiOutput<?> badRequestExceptionHandler(Exception ex, WebRequest request){
        return new ApiOutput<String>(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ApiOutput<?> constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request){
        var errorMap = new HashMap<String, String>();
        for (var violation : ex.getConstraintViolations()) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMap);
    }


    @ExceptionHandler(value = {Exception.class})
    public ApiOutput<?> genericExceptionHandler(Exception ex, WebRequest request){
        log.error("Error Stack.", ex);
        return new ApiOutput<String>(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = {ExcelRecordException.class})
    public ApiOutput<?> handleExcelProcessingException(ExcelRecordException ex){
        var errors = ex.getErrors();
        return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(),"Bad request", errors);
    }
}

