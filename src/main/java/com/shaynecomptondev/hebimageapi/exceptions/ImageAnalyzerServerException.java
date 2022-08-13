package com.shaynecomptondev.hebimageapi.exceptions;

public class ImageAnalyzerServerException extends RuntimeException {
    public ImageAnalyzerServerException()  {
        super("An error occurred while attempting to contact the image detection api. This may indicate that the service is currently unavailable. Please try your request later ");
    }
}
