package com.epam.esm.controller.errorhandler;


import com.epam.esm.controller.exceptions.RestControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    private final static String responseMessage = """
            {
            "errorMessage":"%s",
            "error code":"%s",
            "HTTP status":"%s",
            "Cause:":"%s",
            }""";
    private final static Map<String, HttpStatus> codesAndStatuses = new HashMap<>();

    static {
        codesAndStatuses.put("errorCode=1", HttpStatus.NOT_FOUND);
        codesAndStatuses.put("errorCode=2", HttpStatus.INTERNAL_SERVER_ERROR);
        codesAndStatuses.put("errorCode=3", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RestControllerException.class)
    public ResponseEntity<String> handleCustomException(RestControllerException resException) {
        HttpStatus status = codesAndStatuses.get(resException.getErrorCode());
        Errors errors = resException.getErrors();
        StringBuilder causeMessage;
        if (errors != null) {
            List<ObjectError> errorList = errors.getAllErrors();
            causeMessage = new StringBuilder();
            for (int i = 0; i < errorList.size(); i++) {
                causeMessage.append(errorList.get(i).getDefaultMessage());
                if (i != errorList.size() - 1) causeMessage.append(", ");
            }
        } else {
            causeMessage = new StringBuilder(resException.getMessage());
        }
        String response = String.format(responseMessage, resException.getMessage(), resException.getErrorCode(),
                status, causeMessage);
        return new ResponseEntity<>(response, status);
    }
}