package com.shaynecomptondev.hebimageapi.dtos;

public class ImageDto {
    private int id;
    private byte[] content;
    private String name;
    private ImageObjectsDto[] detectedObjects;
    private ImageMetadataDto[] metadata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageObjectsDto[] getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(ImageObjectsDto[] detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

    public ImageMetadataDto[] getMetadata() {
        return metadata;
    }

    public void setMetadata(ImageMetadataDto[] metadata) {
        this.metadata = metadata;
    }
}
