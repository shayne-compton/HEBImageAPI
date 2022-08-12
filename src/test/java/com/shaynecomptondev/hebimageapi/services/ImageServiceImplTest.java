package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.entities.Image;
import com.shaynecomptondev.hebimageapi.exceptions.ImageNotFoundException;
import com.shaynecomptondev.hebimageapi.repositories.ImageRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ImageAnalyzer imageAnalyzer;
    @InjectMocks
    private ImageServiceImpl imageService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllImages() {
        int totalRecordsToCreate = 10;
        List<Image> imageList = createActiveImageList(totalRecordsToCreate);
        Mockito.when(imageRepository.findAllActive()).thenReturn(imageList);
        Iterable<ImageDto> imageDtos = imageService.GetAllImages();
        int recordsReturned = 0;
        for (ImageDto imageDto : imageDtos) {
            recordsReturned++;
        }
        int finalRecordsReturned = recordsReturned;
        assertAll(  () -> assertEquals(totalRecordsToCreate, finalRecordsReturned));
    }

    @Test
    void getImagesByObjects() {
    }

    @Test
    void getExistingImageById() {
        Image image = createImage(1, "File", new Date(), true);
        image.setId(1);
        Mockito.when(imageRepository.findById(1)).thenReturn(Optional.of(image));
        ImageDto imageDto = imageService.GetImageById(1);
        assertAll(  () -> assertNotNull(image),
                    () -> assertEquals(1, imageDto.getId()),
                    () -> assertNotNull(imageDto.getContent()));
    }

    @Test
    void getNonExistingImageById() {
        assertAll(  () -> assertThrows(ImageNotFoundException.class, () -> {
            ImageDto imageDto = imageService.GetImageById(1);
        }) );
    }

    @Test
    void saveImage() {
    }

    private Image createImage(int id, String source, Date createDate, boolean isActive) {
        byte[] content = getImageContent();
        Image image = new Image();
        image.setId(id);
        image.setContent(content);
        image.setSource(source);
        image.setCreateDate(createDate);
        image.setActive(isActive);
        return image;
    }

    private List<Image> createActiveImageList(int total) {
        ArrayList<Image> imageList = new ArrayList<>();
        int currentImageId = 1;
        String imageSource = "";
        Date today = new Date();
        boolean isActive = true;
        Image tempImage = new Image();
        for (int i = 0; i < total; i++) {
            if (i % 2 == 0) {
                imageSource = "http://example.com/example.jpg";
            } else {
                imageSource = "File";
            }
            tempImage = createImage(currentImageId, imageSource, today, isActive);
            imageList.add(tempImage);
            currentImageId++;
        }

        return imageList;
    }

    private byte[] getImageContent() {
        File fi = new File("testImage.jpg");
        try {
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}