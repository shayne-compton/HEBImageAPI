package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageMetadataDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;

public interface ImageService {
    Iterable<ImageDto> GetAllImages();
    Iterable<ImageDto> GetImagesByObjects(String objects);
    ImageDto GetImageById(int imageId);
    ImageDto SaveImage(ImageUploadDto image);
    Iterable<ImageMetadataDto> GetImageMetadata(byte[] imageContent);
}
