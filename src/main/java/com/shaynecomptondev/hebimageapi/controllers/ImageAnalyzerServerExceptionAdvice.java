package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.exceptions.ImageAnalyzerServerException;
import com.shaynecomptondev.hebimageapi.exceptions.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles ImageAnalyzerServerExceptions
 *
 * @author Shayne Compton
 */
@ControllerAdvice
public class ImageAnalyzerServerExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(ImageAnalyzerServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String imageAnalyzerServerException(ImageAnalyzerServerException ex)
    {
        return ex.getMessage();
    }
}
