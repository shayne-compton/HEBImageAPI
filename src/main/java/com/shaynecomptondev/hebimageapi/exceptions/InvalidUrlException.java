package com.shaynecomptondev.hebimageapi.exceptions;

/**
 * @author Shayne Compton
 */
public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String url)  {
        super("Invalid url supplied " + url);
    }
}
