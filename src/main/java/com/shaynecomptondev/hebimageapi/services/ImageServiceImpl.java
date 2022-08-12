package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;
import com.shaynecomptondev.hebimageapi.entities.Image;
import com.shaynecomptondev.hebimageapi.exceptions.ImageNotFoundException;
import com.shaynecomptondev.hebimageapi.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;


public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageAnalyzer imageAnalyzer;

    @Override
    public Iterable<ImageDto> GetAllImages() {
        Iterable<Image> images = imageRepository.findAllActive();
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images)
        {
            imageDtos.add(MapEntityToDto(image));
        }
        return imageDtos;
    }

    @Override
    public Iterable<ImageDto> GetImagesByObjects(String objects) {
        //strip quotes of beginning and end of string, if they exist
        objects = objects.replaceAll("^\"|\"$", "");
        String[] tokenizedObjects = objects.split(",");
        //todo validate input parameters
        Iterable<Image> images = imageRepository.findAllByObjectActive(tokenizedObjects);
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images)
        {
            imageDtos.add(MapEntityToDto(image));
        }
        return imageDtos;
    }

    @Override
    public ImageDto GetImageById(int imageId) {
        //todo validate input parameters
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()) {
            ImageDto imageDto = MapEntityToDto(image.get());
            return imageDto;
        } else {
            throw new ImageNotFoundException(imageId);
        }
    }

    @Override
    public ImageDto SaveImage(ImageUploadDto image) {
        //todo validate input parameters
        boolean shouldDetectObjects = image.isEnableObjectDetection();
        //todo check if image is from url or byte array
        byte[] imageContents = null;
        String imageUrl = image.getImageUrl();
        if (imageUrl != null)
        {
            imageContents = DownloadImageFromUrl(imageUrl);
        }
        else
        {
            imageContents = image.getImage();
        }

        //todo map imagedto to image entity
        Image newImage = new Image();

        //Detect objects if specified
        Iterable<ImageObjectsDto> detectedObjects = null;
        if (shouldDetectObjects)
        {
            detectedObjects = imageAnalyzer.DetectObjects(imageContents);
        }
        //save our image
        newImage = imageRepository.save(newImage);
        ImageDto newImageDto = MapEntityToDto(newImage);
        return newImageDto;
    }

    private byte[] DownloadImageFromUrl(String url) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                byteArrayOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Image could not be loaded from supplied URL");
        }
    }

    private ImageDto MapEntityToDto(Image image) {
        ImageDto imageDto = new ImageDto();
        //todo Add mapping operations. Maybe this function should go in a mapper class...
        return imageDto;
    }
}
