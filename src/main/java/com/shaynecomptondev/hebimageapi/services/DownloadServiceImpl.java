package com.shaynecomptondev.hebimageapi.services;

import com.shaynecomptondev.hebimageapi.exceptions.InvalidUrlException;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadServiceImpl implements DownloadService {

    @Override
    public byte[] DownloadFileFromUrl(String url) {
        ValidateDownloadFileFromUrlParameters(url);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                byteArrayOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("File could not be loaded from supplied URL");
        }
    }

    private void ValidateDownloadFileFromUrlParameters(String url) {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url)) {
            throw new InvalidUrlException(url);
        }
    }
}
