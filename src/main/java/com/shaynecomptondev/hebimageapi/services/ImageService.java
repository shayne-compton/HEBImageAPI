package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageMetadataDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;

/**
 * Provides functions to deal with images
 *
 * @author Shayne Compton
 *
 */
public interface ImageService {
    /**
     * @return all images
     */
    Iterable<ImageDto> GetAllImages();

    /**
     * @param objects string representation of objects to filter by
     * @return all images for specified object(s)
     */
    Iterable<ImageDto> GetImagesByObjects(String objects);

    /**
     * @param imageId the image id to search for
     * @return the specified image
     */
    ImageDto GetImageById(int imageId);

    /**
     * @param image parameters specifying how to process an image
     * @return the processed image
     */
    ImageDto SaveImage(ImageUploadDto image);

    /**
     * @param imageContent byte[] representing an image file
     * @return Metadata extracted from image
     */
    Iterable<ImageMetadataDto> GetImageMetadata(byte[] imageContent);
}
