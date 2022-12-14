package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.exceptions.ImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles ImageNotFoundExceptions
 *
 * @author Shayne Compton
 */
@ControllerAdvice
public class ImageNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String imageNotFoundHandler(ImageNotFoundException ex)
    {
        return ex.getMessage();
    }
}
