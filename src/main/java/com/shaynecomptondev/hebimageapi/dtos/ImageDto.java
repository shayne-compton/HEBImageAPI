package com.shaynecomptondev.hebimageapi.dtos;

/**
 * @author Shayne Compton
 */
public class ImageDto {
    private int id;
    private byte[] content;
    private String label;
    private ImageObjectsDto[] detectedObjects;
    private ImageMetadataDto[] metadata;

    public ImageDto() { }

    public ImageDto(int id, byte[] content, String label, ImageObjectsDto[] detectedObjects, ImageMetadataDto[] metadata) {
        this.id = id;
        this.content = content;
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        private String label;
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

        public ImageDtoBuilder setLabel(String label) {
            this.label = label;
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

        public ImageDto build() { return new ImageDto(id, content, label, detectedObjects, metadata); }
    }
}
