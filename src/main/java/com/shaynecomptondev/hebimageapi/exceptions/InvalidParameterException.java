package com.shaynecomptondev.hebimageapi.exceptions;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message)  {
        super("Invalid parameters supplied: " + message);
    }
}
