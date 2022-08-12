package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;

public class MockImageAnalyzer implements ImageAnalyzer {
    @Override
    public Iterable<ImageObjectsDto> DetectObjects(byte[] content) {
        return null;
    }
}
