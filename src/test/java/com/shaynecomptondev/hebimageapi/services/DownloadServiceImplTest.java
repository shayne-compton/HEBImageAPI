package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.exceptions.InvalidUrlException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class DownloadServiceImplTest {

    private DownloadServiceImpl downloadService;

    @BeforeEach
    void setUp() {
        downloadService = new DownloadServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void downloadFileFromUrlWithInvalidUrl() {
        assertAll(  () -> assertThrows(InvalidUrlException.class, () -> {
                        downloadService.DownloadFileFromUrl("");}),
                    () -> assertThrows(InvalidUrlException.class, () -> {
                        downloadService.DownloadFileFromUrl("www.");}),
                    () -> assertThrows(InvalidUrlException.class, () -> {
                        downloadService.DownloadFileFromUrl("ww.badaddress.");}));
    }

    //This is technically an integration test
    @Test
    void downloadFileFromUrl() {
        String url = "https://www.travelandleisure.com/thmb/5r08blFG40NpECxoOiWm0vM29RQ=/1600x1200/smart/filters:no_upscale()/1980-waikiki-beach-honolulu-WAIKIKIBEACH0521-ed2540b124294e38a0c246a7d21ed967.jpg";
        byte[] content = downloadService.DownloadFileFromUrl(url);
        assertAll(  () -> assertNotNull(content) );
    }
}