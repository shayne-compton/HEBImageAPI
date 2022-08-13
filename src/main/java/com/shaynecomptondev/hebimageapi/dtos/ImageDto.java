package com.shaynecomptondev.hebimageapi.dtos;

public class ImageDto {
    private int id;
    private byte[] content;
    private String name;
    private ImageObjectsDto[] detectedObjects;
    private ImageMetadataDto[] metadata;

    public ImageDto() { }

    public ImageDto(int id, byte[] content, String name, ImageObjectsDto[] detectedObjects, ImageMetadataDto[] metadata) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.detectedObjects = detectedObjects;
        this.metadata = metadata;
    }

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

    public static ImageDtoBuilder builder() { return new ImageDtoBuilder(); }

    public static class ImageDtoBuilder {
        private int id;
        private byte[] content;
        private String name;
        private ImageObjectsDto[] detectedObjects;
        private ImageMetadataDto[] metadata;

        public ImageDtoBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ImageDtoBuilder setContent(byte[] content) {
            this.content = content;
            return this;
        }

        public ImageDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ImageDtoBuilder setDetectedObjects(ImageObjectsDto[] detectedObjects) {
            this.detectedObjects = detectedObjects;
            return this;
        }

        public ImageDtoBuilder setMetadata(ImageMetadataDto[] metadata) {
            this.metadata = metadata;
            return this;
        }

        public ImageDto build() { return new ImageDto(id, content, name, detectedObjects, metadata); }
    }
}
