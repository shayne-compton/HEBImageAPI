package com.shaynecomptondev.hebimageapi.services;

/**
 * Provides capability to download a file from a webpage
 *
 * @author Shayne Compton
 */
public interface DownloadService {
    /**
     * @param url A valid url for the file
     * @return a byte[] for the downloaded file
     */
    byte[] DownloadFileFromUrl(String url);
}
