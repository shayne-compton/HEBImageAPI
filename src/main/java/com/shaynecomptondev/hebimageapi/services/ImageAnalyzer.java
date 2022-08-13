package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;


/**
 * Provides capability to detect objects for images
 *
 * @Author Shayne Compton
 */
public interface ImageAnalyzer {
    /**
     * @param content byte[] representation of an image file
     * @return all detected objects for specified image
     */
    Iterable<ImageObjectsDto> DetectObjects(byte[] content);
}
