package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;

public interface ImageAnalyzer {
    Iterable<ImageObjectsDto> DetectObjects(byte[] content);
}
