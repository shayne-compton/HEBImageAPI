package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;
import com.shaynecomptondev.hebimageapi.entities.Image;
import com.shaynecomptondev.hebimageapi.entities.ImageObject;
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
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ImageAnalyzer imageAnalyzer;
    @Mock
    private DownloadService downloadService;
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
        String objectString = "dog,cat";
        int totalRecordsToCreate = 5;
        List<Image> imageList = createActiveImagesWithObjectsList(totalRecordsToCreate);
        Mockito.when(imageRepository.findAllByObjectActive(Mockito.any())).thenReturn(imageList);
        Iterable<ImageDto> imageDtos = imageService.GetImagesByObjects(objectString);
        int recordsReturned = 0;
        for (ImageDto imageDto : imageDtos) {
            recordsReturned++;
        }
        int finalRecordsReturned = recordsReturned;
        assertAll(  () -> assertEquals(totalRecordsToCreate, finalRecordsReturned));
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
    void saveImageFromUrlWithObjectDetection() {
        String mockUrl = "http://www.example.com/file.jpg";
        ImageUploadDto mockImageUploadDto = ImageUploadDto.builder()
                .setImageUrl("http://www.example.com/file.jpg")
                .setLabel("Example Image")
                .setEnableObjectDetection(true)
                .build();
        Date today = new Date();
        Image image = Image.builder().setId(1).setImageMetadata(null).setImageObjects(createImageObjects()).setContent(getImageContent()).setSource(mockUrl).setCreateDate(today).setActive(true).build();
        Iterable<ImageObjectsDto> detectedObjects = createImageObjectsDto();
        Mockito.when(downloadService.DownloadFileFromUrl(mockUrl)).thenReturn(getImageContent());
        Mockito.when(imageRepository.save(Mockito.any(Image.class))).thenReturn(image);
        Mockito.when(imageAnalyzer.DetectObjects(Mockito.any(byte[].class))).thenReturn(detectedObjects);

        ImageDto newImage = imageService.SaveImage(mockImageUploadDto);

        assertAll(  () -> assertNotNull(newImage),
                    () -> assertEquals(1, newImage.getId()),
                    () -> assertNotNull(newImage.getContent()),
                    () -> assertNotNull(newImage.getDetectedObjects()),
                    () -> assertEquals(2, newImage.getDetectedObjects().length));
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

    private List<Image> createActiveImagesWithObjectsList(int total) {
        ArrayList<Image> imageList = new ArrayList<>();
        Date today = new Date();
        Image tempImage = null;
        int currentImageId = 1;
        for (int i = 0; i < total; i++) {
            Image.ImageBuilder builder = Image.builder().setId(currentImageId).setContent(getImageContent()).setCreateDate(today).setImageObjects(createImageObjects()).setActive(true);
            if (i % 2 == 0) {
                builder.setSource("http://example.com/example.jpg");
            } else {
                builder.setSource("File");
            }
            tempImage = builder.build();
            imageList.add(tempImage);
            currentImageId++;
        }

        return imageList;
    }

    private Set<ImageObject> createImageObjects() {
        Date today = new Date();
        HashSet<ImageObject> imageObjects = new HashSet<>();
        ImageObject dogObject = ImageObject.builder().setId(1).setName("dog").setScore(0.92).setActive(true).setCreateDate(today).build();
        ImageObject catObject = ImageObject.builder().setId(1).setName("cat").setScore(0.87).setActive(true).setCreateDate(today).build();
        imageObjects.add(dogObject);
        imageObjects.add(catObject);
        return imageObjects;
    }

    private Iterable<ImageObjectsDto> createImageObjectsDto() {
        ArrayList<ImageObjectsDto> detectedObjects = new ArrayList<>();
        detectedObjects.add(ImageObjectsDto.builder().setName("dog").setScore("0.923").build());
        detectedObjects.add(ImageObjectsDto.builder().setName("cat").setScore("0.871").build());
        return detectedObjects;
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