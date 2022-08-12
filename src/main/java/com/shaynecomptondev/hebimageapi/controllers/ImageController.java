package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;
import com.shaynecomptondev.hebimageapi.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/images")
    @ResponseBody
    Iterable<ImageDto> all(@RequestParam(required = false) String objects) {
        Iterable<ImageDto> images;
        if (objects != null) {
            images = imageService.GetImagesByObjects(objects);
        } else {
            images = imageService.GetAllImages();
        }
        return images;
    }

    @GetMapping("/images/{id}")
    ImageDto one(@PathVariable int id) {
        ImageDto image = imageService.GetImageById(id);
        return image;
    }

    @PostMapping("/images")
    ImageDto save(@RequestBody ImageUploadDto newImage)
    {
        ImageDto image = imageService.SaveImage(newImage);
        return image;
    }
}
