package com.shaynecomptondev.hebimageapi.exceptions;

/**
 * @author Shayne Compton
 */
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message)  {
        super("Invalid parameters supplied: " + message);
    }
}
