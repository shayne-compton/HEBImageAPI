package com.shaynecomptondev.hebimageapi.exceptions;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String url)  {
        super("Invalid url supplied " + url);
    }
}
