package com.shaynecomptondev.hebimageapi.controllers;

import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.services.ImageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private ImageController controller;

    @Test
    void contextLoads() throws Exception {
        assertNotNull(controller);
    }
}