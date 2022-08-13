package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class GoogleImageAnalyzerTest {

    private ImageAnalyzer imageAnalyzer;

    @BeforeEach
    void setUp() {
        String visionEnpointUrl = "https://vision.googleapis.com/v1/images:annotate";
        String visionApiKey = "Your API key here";
        imageAnalyzer = new GoogleImageAnalyzer(visionEnpointUrl, visionApiKey);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void detectObjectsWithNullImage() {
        assertAll(  () -> assertThrows(IllegalArgumentException.class, () -> {
            imageAnalyzer.DetectObjects(null);
        }) );
    }

    @Test
    void detectObjects() {
        byte[] imageContent = getImageContent();
        Iterable<ImageObjectsDto> detectedObjects = imageAnalyzer.DetectObjects(imageContent);
        assertAll(  () -> assertNotNull(detectedObjects));
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