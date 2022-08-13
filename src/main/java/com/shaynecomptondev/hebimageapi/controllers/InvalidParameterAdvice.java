package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.exceptions.ImageNotFoundException;
import com.shaynecomptondev.hebimageapi.exceptions.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles InvalidParameterExceptions
 *
 * @author Shayne Compton
 */
@ControllerAdvice
public class InvalidParameterAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidParametersHandler(InvalidParameterException ex)
    {
        return ex.getMessage();
    }
}
