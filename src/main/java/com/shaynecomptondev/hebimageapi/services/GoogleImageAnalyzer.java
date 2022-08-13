package com.shaynecomptondev.hebimageapi.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaynecomptondev.hebimageapi.dtos.GoogleImageDetectionResponseDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import com.shaynecomptondev.hebimageapi.exceptions.ImageAnalyzerClientException;
import com.shaynecomptondev.hebimageapi.exceptions.ImageAnalyzerServerException;
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

    public GoogleImageAnalyzer() {
    }

    public GoogleImageAnalyzer(String imageUri, String apiKey) {
        this.imageUri = imageUri;
        this.apiKey = apiKey;
    }

    @Override
    public Iterable<ImageObjectsDto> DetectObjects(byte[] content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        String uri = imageUri + "?key=" + apiKey;
        String encodedContent = Base64.getEncoder().encodeToString(content);
        String jsonInputString = "{" +
                "\"requests\":[" +
                "{" +
                "\"image\":{" +
                "\"content\":\"" + encodedContent + "\"" +
                "}," +
                "\"features\":[" +
                "{" +
                "\"type\":\"LABEL_DETECTION\"," +
                "\"maxResults\":10" +
                "}" +
                "]" +
                "}" +
                "]" +
                "}";
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400 && response.statusCode() <= 499) {
                throw new ImageAnalyzerClientException("response code: " + response.statusCode());
            } else if (response.statusCode() >= 500 && response.statusCode() <= 599) {
                throw new ImageAnalyzerServerException();
            }

            String jsonResponse = response.body();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode node = mapper.readTree(jsonResponse);

            GoogleImageDetectionResponseDto googleResponse = mapper.treeToValue(node, GoogleImageDetectionResponseDto.class);

            List<ImageObjectsDto> detectedImages = new ArrayList<>();

            ImageObjectsDto temp;
            for (GoogleImageDetectionResponseDto.Response.LabelAnnotation annotation : googleResponse.responses[0].labelAnnotations)
            {
                temp = new ImageObjectsDto(annotation.description, annotation.score);
                detectedImages.add(temp);
            }
            return detectedImages;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
