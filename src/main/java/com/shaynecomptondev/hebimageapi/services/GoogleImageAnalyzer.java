package com.shaynecomptondev.hebimageapi.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaynecomptondev.hebimageapi.dtos.GoogleImageDetectionResponseDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GoogleImageAnalyzer implements ImageAnalyzer {

    @Value("${GoogleVisionUri}")
    private String imageUri;
    @Value("${GoogleVisionApiKey}")
    private String apiKey;


    @Override
    public Iterable<ImageObjectsDto> DetectObjects(byte[] content) {
        return null;
    }
}
