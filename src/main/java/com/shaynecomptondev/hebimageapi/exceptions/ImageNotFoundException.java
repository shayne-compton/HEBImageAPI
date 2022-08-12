package com.shaynecomptondev.hebimageapi.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(int id) {
        super("Could not find image " + id);
    }
}
