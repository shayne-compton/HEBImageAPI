package com.shaynecomptondev.hebimageapi.exceptions;

public class ImageAnalyzerClientException extends RuntimeException {
    public ImageAnalyzerClientException(String message)  {
        super("A client-side exception occurred while trying to contact the image anazlyzer api: " + message);
    }

}
