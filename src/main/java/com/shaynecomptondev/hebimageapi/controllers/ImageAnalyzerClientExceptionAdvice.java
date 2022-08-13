package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.exceptions.ImageAnalyzerClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles ImageAnalyzerClientExceptions
 *
 * @author Shayne Compton
 */
@ControllerAdvice
public class ImageAnalyzerClientExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(ImageAnalyzerClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String imageAnalyzerServerException(ImageAnalyzerClientException ex)
    {
        return ex.getMessage();
    }
}
